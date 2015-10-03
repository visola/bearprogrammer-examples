var bodyParser = require('body-parser');
var express = require('express');
var merge = require('merge');

var app = express();
app.use(express.static('static'));
app.use(bodyParser.json());

var contactsById = {};
var lastId = 0;

app.route('/api/v1/contacts/:id?')
  .get(function (req, resp) {
    var result = [];
    for (var n in contactsById) {
      result.push(contactsById[n]);
    }
    resp.json(result);
  })
  .post(function (req, resp) {
    var contact = req.body,
      id = ++lastId;
    contact.id = id;
    contactsById[id] = contact;
    resp.json(contact);
  })
  .put(function (req, resp) {
    var contactId = req.params.id,
      contact = contactsById[contactId];
    if (contact) {
      contact = merge(contact, req.body);
      contactsById[contactId] = contact;
      resp.json(contact)
    } else {
      resp.status(404).send('Contact not found');
    }
  });

var server = app.listen(3000, function () {
  var address = server.address(),
    host = address.address,
    port = address.port;

  console.log("Example app listening at http://%s:%s", host, port);
});