const {client} = require('nightwatch-cucumber');
const {defineSupportCode} = require('cucumber');

defineSupportCode(({Given, Then, When}) => {
  /*
  Given(/^I open "([^"]*)"$/, (url) => {
    return client
      .url(url)
      .waitForElementVisible('body', 1000);
  });*/

  Given('I open Flight Finder', () => {
    const fflogin = client.page.fflogin()

    return fflogin
      .navigate()
      .waitForElementVisible('@body', 1000)
  });

  When(/^I type "(.*?)" as username$/, (username) => {
    const fflogin = client.page.fflogin()

    return fflogin.setValue('@username', username)
  });

  When(/^I type "(.*?)" as password$/, (password) => {
    const fflogin = client.page.fflogin()

    return fflogin.setValue('@password', password)
  });

  When('I click login', () => {
    const fflogin = client.page.fflogin()

    return fflogin.click('@login_button')
  });

  When(/^I wait "(.*?)"$/, (wait_time) => {
    return client.pause(wait_time);
  });

  Then(/^the title is "(.*?)"$/, (text) => {
    return client.assert.title(text)
  });
  /*
  Then(/^the "([^"]*)" form with "([^"]*)" "([^"]*)" exists$/, (object_type, object_property, object_value) => {
    return client
      .assert.visible(object_type+'['+object_property+'='+object_value+']');
  });*/

  Then('the username form exists', () => {
    const fflogin = client.page.fflogin()

    return fflogin.assert.visible('@username')
  });

  Then('the password form exists', () => {
    const fflogin = client.page.fflogin()

    return fflogin.assert.visible('@password')
  });

});
