define(["backbone", "jquery"],
  function (Backbone, $) {
    function getContentElement() {
      return $('#content');
    };

    function render(view) {
      view.render();
      getContentElement().html(view.$el.get(0));
    };

    var Router = Backbone.Router.extend({
      routes : {
        "(/)" : "contacts",
        "/contacts(/)" : "contacts"
      },

      contacts : function () {
        require(['view/contact/List'], function (ListContactsView) {
          render(new ListContactsView());
        });
      }
    });

    return new Router();
  }
);
