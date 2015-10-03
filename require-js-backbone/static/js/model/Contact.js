define(['backbone'], function (Backbone) {
  return Backbone.Model.extend({
    urlRoot : '/api/v1/contacts'
  });
});