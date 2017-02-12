'use strict';

var app = angular.module('topword', ['ngSanitize', 'ui.select']);

app.controller('topwords',function ($scope, $http) {
    var vm = this;

    vm.word = {};
    vm.refreshWords = function(prefix) {
        if (!prefix) {
            return
        }
        var params = {prefix: prefix, size: 10};
        return $http.get(
            '/words/most',
            {params: params}
        ).then(function(response) {
            vm.words = response.data;
        });
    };
});