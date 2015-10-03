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
        "(/)" : "contacts",
        "/contacts(/)" : "contacts"
      },

      contacts : function () {
        require(['view/Home'], function (HomeView) {
          render(new HomeView());
        });
      }
    });

    return new Router();
  }
);
