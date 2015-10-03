define(['backbone', 'router', 'tpl!template/home.html', 'collection/Contacts'], function (Backbone, router, HomeTemplate, Contacts) {
  return Backbone.View.extend({
    template: HomeTemplate,
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
    }
  });
});