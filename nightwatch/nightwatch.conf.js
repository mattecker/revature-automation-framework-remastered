// use JavaScript conf file so we can include comments and helper functions
require('nightwatch-cucumber')({
  'cucumberArgs': ['--require', 'timeout.js', '--require', 'features/step_definitions', '--format', 'pretty', '--format', 'json:reports/cucumber.json', 'features']
})

module.exports = {
  "src_folders": [
    "tests"    // location of e2e tests
  ],
  "output_folder": "./reports", // reports (test outcome) output by nightwatch
  //'globals_path': "./data/dev.js",
  'page_objects_path': 'page-objects',
  "selenium": {
    "start_process": true,
    "server_path": "./selenium-server-standalone-3.0.1.jar",
    "port": 4444, // standard selenium port
    "cli_args": {
      "webdriver.chrome.driver" : "/usr/local/bin/chromedriver", /*"./node_modules/nightwatch/bin/chromedriver",*/
      "webdriver.gecko.driver" : "/usr/local/bin/geckodriver"
    }
  },
  "test_settings": {
    "default": {
      "selenium_port"  : 4444,
      "selenium_host"  : "localhost",
      "screenshots": {
        "enabled": true, // if you want to keep screenshots
        "path": './screenshots' // save screenshots here
      },
      "globals": {
        "waitForConditionTimeout": 10000, // time to wait for a condition
        'client': './data/dev.js'
      },
      "desiredCapabilities": { // default browser for tests
        "browserName": "firefox"
      }
    },
    "firefox": {
      "desiredCapabilities": {
        "browserName": "firefox",
        "javascriptEnabled": true // turn off to test progressive enhancement
      }
    },
    "ci-server-firefox" : {   // custom test environment that overwrites default settings when used as either '-e ci-server-firefox' or '--env ci-server-firefox'
      "integration" : true,
      "desiredCapabilities": {
        "browserName": "firefox",
        "marionette": true
      }
    },
    'ci-server-chrome' : {
      "integration" : true,
      "desiredCapabilities": {
        "browserName": "chrome",
        "marionette": true
      }
    }
  }
}
