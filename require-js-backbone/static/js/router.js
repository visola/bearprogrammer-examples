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
        "contacts(/)" : "contacts",
        "contacts/:id(/)" : "editContact"
      },

      contacts : function () {
        require(['view/contact/List'], function (ListContactsView) {
          render(new ListContactsView());
        });
      },

      editContact: function (id) {
        require(['view/contact/Edit', 'model/Contact'], function (EditContactView, Contact) {
          var contact = new Contact();
          if (id != 'new') {
            contact.set('id', id);
          }
          render(new EditContactView({model:contact}));
        });
      }
    });

    return new Router();
  }
);
