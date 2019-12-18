// This directive is to set focus on element.
// To use this, just put "v-focus" on element html tag.
Vue.directive('focus', {
	inserted: function (el) {
		el.focus();
	}
});

