/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
package id.io.asset.util.database;

import id.io.asset.model.MemberLevelModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

public class MemberLevelDatabaseHelper extends BaseDatabaseHelper {

    public MemberLevelDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public List<MemberLevelModel> levelList() {
        List<MemberLevelModel> userLevelList = new ArrayList<>();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT levelid, levelcode, levelname, description, IF(isactive, 'true', 'false') isactive FROM user_level;";

        try (Handle h = getHandle()) {
            userLevelList = h.createQuery(sql).mapToBean(MemberLevelModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return userLevelList;
    }

    public MemberLevelModel getLevelById(String levelId) {

        MemberLevelModel userLevel = new MemberLevelModel();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT levelid, levelcode, levelname, description, IF(isactive, 'true', 'false') isactive FROM user_level WHERE levelid = :levelId;";

        try (Handle h = getHandle()) {
            userLevel = h.createQuery(sql).bind("levelId", levelId).mapToBean(MemberLevelModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }

        return userLevel;
    }

    public int create(MemberLevelModel model) {
        log.debug(MemberLevelDatabaseHelper.class.getName(), "- createMemberLevel");

        final String sql = "INSERT INTO user_level (levelid, levelcode, levelname, description, isactive) VALUES( :levelid, :levelcode, :levelname, :description, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", model.getLevelid())
                    .bind("levelcode", model.getLevelcode())
                    .bind("levelname", model.getLevelname())
                    .bind("description", model.getDescription())
                    .bind("isactive", false).execute();

        } catch (SQLException ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorCreateMemberLevel " + ex);
        }
        return row;
    }

    public int update(String levelId, MemberLevelModel model) {
        log.debug(MemberLevelDatabaseHelper.class.getName(), "- updateMemberLevel");

        final String sql = "UPDATE user_level SET levelname = :levelname, description = :description WHERE levelid = :levelid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", levelId)
                    .bind("levelname", model.getLevelname())
                    .bind("description", model.getDescription()).execute();

        } catch (SQLException ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorUpdateMemberLevel " + ex);
        }
        return row;
    }

    public int activate(String levelId, boolean isActive) {
        log.debug(MemberLevelDatabaseHelper.class.getName(), "- activateMemberLevel");

        final String sql = "UPDATE user_level SET isactive = :isactive WHERE levelid = :levelid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", levelId)
                    .bind("isactive", isActive).execute();

        } catch (SQLException ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorActivateMemberLevel " + ex);
        }
        return row;
    }

    public int remove(String levelId) {

        log.debug(ConfigurationDatabaseHelper.class.getName(), "- deleteMemberLevel");

        final String sql = "DELETE FROM user_level WHERE levelId = :levelId;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind("levelId", levelId).execute();

        } catch (SQLException ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorDeleteMemberLevel " + ex);
        }
        return result;
    }

}
