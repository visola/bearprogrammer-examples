var bodyParser = require('body-parser');
var express = require('express');
var merge = require('merge');

var app = express();
app.use(express.static('static'));
app.use(bodyParser.json());

var contactsById = {};
var lastId = 0;

function sortContact(a, b) {
  var result = a.firstName.toLowerCase().localeCompare(b.firstName.toLowerCase());
  if (result == 0) {
    result = a.lastName.toLowerCase().localeCompare(b.lastName.toLowerCase());
  }
  return result;
}

app.route('/api/v1/contacts/:id?')
  .get(function (req, resp) {
    var contactId = req.params.id,
      contact,
      result = [];
    if (contactId === undefined) {
      for (var n in contactsById) {
        result.push(contactsById[n]);
      }
      result.sort(sortContact);
      resp.json(result);
    } else {
      contact = contactsById[contactId];
      if (contact) {
        resp.json(contact);
      } else {
        resp.status(404).send('Contact not found');
      }
    }
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

app.get('*', function(request, response){
  response.sendfile('static/index.html');
});

var server = app.listen(3000, function () {
  var address = server.address(),
    host = address.address,
    port = address.port;

  console.log("Example app listening at http://%s:%s", host, port);
});