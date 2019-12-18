package id.io.asset.controller;

//
//package id.io.dfm.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.jdbi.v3.core.Handle;
//import org.jdbi.v3.core.statement.Update;
//import sg.ic.umx.model.Application;
//
//public class ApplicationController extends BaseController {
//
//
//    public ApplicationController() {
//        log = getLogger(this.getClass());
//    }
//
//    public boolean create(Application application) {
//        final String methodName = "create";
//        start(methodName);
//        final String sql =
//                "INSERT INTO [application] (name,configurationName,recipientList, attributeList,mailSubject,mailBody) "
//                        + "VALUES (:name, :configurationName, :recipientList, :attributeList,:mailSubject,:mailBody)";
//        boolean result = false;
//        try (Handle h = getHandle()) {
//            Update update = h.createUpdate(sql).bindBean(application);
//            result = executeUpdate(update);
//
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result;
//    }
//
//    public boolean update(Application application) {
//        final String methodName = "update";
//        start(methodName);
//        final String sql =
//                "UPDATE [application] set name=:name, configurationName=:configurationName, recipientList = :recipientList, "
//                        + "attributeList=:attributeList , mailSubject =:mailSubject, mailBody = :mailBody WHERE id = :id";
//        boolean result = false;
//        try (Handle h = getHandle()) {
//            Update update = h.createUpdate(sql).bindBean(application);
//            result = executeUpdate(update);
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result;
//    }
//
//    public boolean delete(long id) {
//        final String methodName = "delete";
//        start(methodName);
//        final String sql = "DELETE FROM [application] WHERE id = :id";
//        boolean result = false;
//        try (Handle h = getHandle()) {
//            Update update = h.createUpdate(sql).bind("id", id);
//            result = executeUpdate(update);
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result;
//    }
//
//    public Application get(long id) {
//        final String methodName = "get";
//        start(methodName);
//        final String sql = "SELECT * FROM [application] WHERE id = :id";
//        Optional<Application> result = Optional.empty();
//        try (Handle h = getHandle()) {
//            result = h.createQuery(sql).bind("id", id).mapToBean(Application.class).findFirst();
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result.isPresent() ? result.get() : null;
//    }
//
//    public List<Application> list() {
//        final String methodName = "list";
//        start(methodName);
//        final String sql = "SELECT * FROM [application] ORDER BY name, id";
//        List<Application> result = new ArrayList<>();
//        try (Handle h = getHandle()) {
//            result = h.createQuery(sql).mapToBean(Application.class).list();
//            h.close();
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result;
//    }
//
//    public int getCountByName(String name) {
//        final String methodName = "getCountByName";
//        start(methodName);
//        final String sql = "SELECT COUNT(*) FROM [application] WHERE name = :name";
//        int result = 0;
//        try (Handle h = getHandle()) {
//            result = h.createQuery(sql).bind("name", name).mapTo(Integer.class).one();
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//        completed(methodName);
//        return result;
//    }
//}
