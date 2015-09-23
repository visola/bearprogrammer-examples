define(["backbone"], 
  function (Backbone) {
    function getContentElement() {
      return document.getElementById('content');
    };

    function render(view) {
      view.render();
      getContentElement().appendChild(view.$el.get(0));
    };

    var Router = Backbone.Router.extend({
      routes : {
        "(/)" : "home",
        "contacts(/)" : "addContact",
        "contacts/:id(/)" : "editContact"
      },

      home : function () {
        require(['view/Home'], function (HomeView) {
          render(new HomeView());
        });
      }
    });

    return new Router();
  }
);
