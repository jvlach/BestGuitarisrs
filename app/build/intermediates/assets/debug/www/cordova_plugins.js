cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
                  {
                      "file": "plugins/cordova-plugin-whitelist/whitelist.js",
                      "id": "cordova-plugin-whitelist.whitelist",
                      "runs": true
                  },
    {
        "file": "plugins/org.apache.cordova.inappbrowser/www/inappbrowser.js",
        "id": "org.apache.cordova.inappbrowser.inappbrowser",
        "clobbers": [
            "window.open"
        ]
    },
    {
        "file": "plugins/cordova-plugin-admobpro/www/AdMob.js",
        "id": "cordova-plugin-admobpro.AdMob",
        "clobbers": [
            "window.AdMob"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "org.apache.cordova.inappbrowser": "0.5.2"
}
// BOTTOM OF METADATA
});