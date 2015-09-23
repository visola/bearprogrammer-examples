define(['backbone', 'tpl!template/home.html'], function (Backbone, HomeTemplate) {
  return Backbone.View.extend({
    template: HomeTemplate,
    render: function () {
      this.$el.html(this.template());
    }
  });
});