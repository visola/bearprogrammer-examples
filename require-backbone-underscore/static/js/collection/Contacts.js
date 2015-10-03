define(['backbone'], function (Backbone) {
  return Backbone.Collection.extend({
    url: '/api/v1/contacts'
  });
});