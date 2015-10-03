define(['backbone', 'router', 'tpl!template/contact/list.html', 'collection/Contacts'], 
    function (Backbone, router, ListContactsTemplate, Contacts) {

  return Backbone.View.extend({
    template: ListContactsTemplate,
    events: {
      'click a': 'routeLink'
    },
    initialize: function () {
      var _this = this;
      this.loading = true;
      this.collection = new Contacts();
      this.collection.fetch().then(function () {
        _this.loading = false;
        _this.render();
      });
    },
    render: function () {
      if (this.loading) {
        this.$el.html("<p>Loading...</p>");
      } else {
        this.$el.html(this.template({collection:this.collection}));
      }
    },
    routeLink: function (e) {
      e.preventDefault();
      router.navigate(e.target.getAttribute('href'), {trigger:true});
    }
  });

});