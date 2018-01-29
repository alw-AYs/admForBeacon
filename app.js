/*
 * @Author: alw-AY's
 * @Date:   2018-01-25 15:46:27
 * @Last Modified by:   alw-AY's
 * @Last Modified time: 2018-01-29 18:58:51
 */

var _ = require('lodash');

var fs = require('fs');

var AdMasterAPI = require('./AdmasterAPI.js');


var api = new AdMasterAPI('jFO6hFvEelWW', 'XTcILLZgLBS8U2kHfmLu6e0EO5JU9M5h');


var session, teamID;


api.onReady(function() {

  var teamId = api.getTeamId(0);
  var ruleId = api.getTeams()[0].rules[0].id;

  console.log(teamId,ruleId);

  api.getData(teamId, {
    "platforms":  [api.getTeams()[0].info.queryTypes[0]],
    "endDate": "2018-01-28 23:59:59",
    "startDate": "2017-09-01 00:00:00",
    "action": "query",
    "ruleId": ruleId,
    "format": "array",
    "maxResults":100
  }, function(_data) {



    _.forEach(_data,function(v){
      console.log(v);
      console.log("\r\n");
    })
  });

})
