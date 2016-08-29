
(function() {

    var app = angular.module('app', [] );


    app.controller('taskController', function ($scope, $http, $timeout) {

        var ctrl = this;

        ctrl.tempTasks = [];
        $scope.receivedData = "";
        $scope.allTasks = [];
        $scope.forms= {};


       /* $scope.testAskServer = function () {
            $http.get("http://localhost:8080/one").
                then(function (response) {
                    $scope.receivedData = response.data;
                    alert($scope.receivedData);
                });

        };*/

        $( document ).ready(function() {
            /*$('select').not('.disabled').material_select();*/
            $('#filterSel').on('change', function(e) {
                alert($("#filterSel").val());

                $http.get("http://localhost:8080/filter/"+$("#filterSel").val()).
                    then(function(response){
                        $scope.allTasks = response.data;

                        $timeout(function() {
                            updateCompletedStatus();
                        }, 2000);
                    });
            });
        });


        function updateCompletedStatus() {
            for (i = 0; i<$scope.allTasks.length; i++) {

                isComp = $scope.allTasks[i].completed;
                switchName = '#sw'+$scope.allTasks[i].id;

                //console.log("was " + $(switchName).prop('checked'));
                $(switchName).prop('checked', isComp);
                //console.log("became " + $(switchName).prop('checked'));
            }
        }

        $timeout(function() {
            updateCompletedStatus();
        }, 2000);

        $scope.getAllTasks = function () {
            $http.get("http://localhost:8080/getAll").
                then(function (response) {
                   $scope.allTasks = response.data;

                });

        };

        $scope.addNewTask = function() {
            ctrl.tempTasks.push("form.tpl.html");
            console.log($scope.forms);
        };

        $scope.checkTaskShown = function(id) {
            for (i =0; i < $scope.allTasks.length; i++) {
                if ($scope.allTasks[i].id == id && $scope.allTasks[i].status == "deleted") {
                    return true;
                }
            }
        };

        $scope.makeDeleted = function(id) {
            for (i =0; i < $scope.allTasks.length; i++) {
                if ($scope.allTasks[i].id == id) {
                    $scope.allTasks[i].status = "deleted";
                }
            }
        };

        $scope.makeCompleted = function(id) {
            for (i =0; i < $scope.allTasks.length; i++) {
                if ($scope.allTasks[i].id==id) {
                    if ($scope.allTasks[i].completed == true) {
                        $scope.allTasks[i].completed = false;
                    }
                    else {
                        $scope.allTasks[i].completed = true;
                    }
                }
            }

        };


        $scope.updateProgress = function() {
            var everything = [];

            // prefill temptask array
            var tempToAdd = [];
            var tempTask;
            for (i = 0; i < ctrl.tempTasks.length; i++) {
                tempTask =
                {
                    "text": $scope.forms[i],
                    "status": "new",
                    "completed": false
                };
                tempToAdd.push(tempTask);
            }

            // prefill everything array
            for (j = 0 ; j < $scope.allTasks.length; j++) {
                everything.push($scope.allTasks[j]);
            }
            // merge two arrays
            for (i = 0 ; i < tempToAdd.length; i++ ) {
                everything.push(tempToAdd[i]);
            }

            // send data to server
            $http.post("http://localhost:8080/updateProgress", everything).
                then(function(response) {
                    $("#filterSel").val("All")
            });
            $timeout(function() {
                $scope.getAllTasks();
                ctrl.tempTasks = [];
            }, 2000);

            $timeout(function() {
                updateCompletedStatus();
            }, 3000);


        }

    });


})()
