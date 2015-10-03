require.config({
  baseUrl: "/js",
  paths: {
    "backbone" : "lib/backbone-1.2.3",
    "jquery" : "lib/jquery-2.1.4",
    "text" : "lib/text-2.0.14",
    "tpl": "lib/tpl-0.3",
    "underscore" : "lib/underscore-1.8.3",

    "template" : "/template"
  }
});

require(['router'], function () {
  Backbone.history.start({pushState: true});
});
