define(['backbone', 'jquery', 'router', 'tpl!template/contact/edit.html'], 
    function (Backbone, $, router, EditContactTemplate) {

  return Backbone.View.extend({
    template: EditContactTemplate,
    events: {
      'click a': 'routeLink',
      'submit form' : 'save'
    },
    initialize: function () {
      var _this = this;
      this.loading = !this.model.isNew();
      if (this.loading) {
        this.model.fetch().then(function () {
          _this.loading = false;
          _this.render();
        });
      }
    },
    render: function () {
      if (this.loading) {
        this.$el.html("<p>Loading...</p>");
      } else {
        this.$el.html(this.template({model:this.model}));
      }
    },
    routeLink: function (e) {
      e.preventDefault();
      router.navigate(e.target.getAttribute('href'), {trigger:true});
    },
    save: function (e) {
      var data = {},
        $form = this.$('form');
      e.preventDefault();
      $form.find('input[type!=submit]').each(function (i, el) {
        var $el = $(el);
        data[$el.attr('name')] = $el.val();
      });
      this.model.save(data, {wait:true,
        success: function () {
          router.navigate('/contacts', {trigger:true});
        }
      });
    }
  });

});