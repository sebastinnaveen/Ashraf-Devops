(function () {

angular.module('mainApp', [
        // Angular modules

        // Custom modules

        // 3rd Party Modules
       
    ])

.config([function () {  
        //console.log("Configuration hook")
    }])

.run([function () {

    //console.log("Run hook")

}])

.service('GlobalStuff', [function () {

    var that = this;
    that.globalModelName = {};

    this.setGlobalModel = function (modelName) {
        if (modelName!==undefined)
        {
            that.globalModelName = modelName;
        }   
    }
 
}])

.service('ApiService', ['GlobalStuff','$http', function (GlobalStuff,$http) {

    var that = this;
    that.modelData = {};

    this.getModelNames = function () {
        //console.log("getModelNames function");
        return $http.get('api/DynamicModels');
    }

    this.getModelStructure = function (modelName) {
        GlobalStuff.setGlobalModel(modelName)
        //console.log("getModelStructure function");
        //console.log(GlobalStuff.globalModelName);
        return $http.get('api/Structure/' + GlobalStuff.globalModelName);
    }
    
    this.getAll = function (modelName) {
        GlobalStuff.setGlobalModel(modelName)
        return $http.get('api/' + GlobalStuff.globalModelName);
    }

    this.getOne = function (modelID) {
        return $http.get('api/' + GlobalStuff.globalModelName + '/' + modelID);
    }

    this.create = function (modelObject) {
        return $http.post('api/' + GlobalStuff.globalModelName, modelObject);
        
    }

    this.update = function (modelID, modelObject) {
        return $http.put('api/' + GlobalStuff.globalModelName + '/' + modelID, modelObject);
        
    }

    this.delete = function (modelID) {
        return $http.delete('api/' + GlobalStuff.globalModelName + '/' + modelID);
    }

}])

.controller('MainCtrl', ['GlobalStuff','ApiService', '$scope', '$http', function (GlobalStuff,ApiService, $scope, $http) {

	this.showCreateAlert = false;
    var scope = this;
    scope.modelData = {};
    scope.HeaderColumnName = {};
    scope.PrimaryKeyName = {};
 
    // 1 ==================================================
    // Get model names for sidebar
    this.getModelNames = function () {

        //console.log("Get Model Names");
        var getModelNamesPromise=ApiService.getModelNames();
        getModelNamesPromise.then(
        function successCallback(response) {
            scope.ContollerModels = response.data;
            //console.log(scope.ContollerModels);
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
    };
  // ==================================================
    
  // 2 ==================================================
    // getAll
      this.loadGrid = function (modelName) {

      	this.loadModelStructure(modelName);
          //console.log("Load Grid");
          var getAllPromise=ApiService.getAll(modelName);
          getAllPromise.then(
          function successCallback(response) {
  			
              scope.modelData = response.data;
              scope.bindSomeCrazyHTML();
              // scope.HeaderColumn = scope.modelData[scope.HeaderColumnName];
              // scope.PrimaryKey=scope.modelData[PrimaryKeyName];
              //console.log(scope.modelData);

          },
          function errorCallback(response) {
              //console.log(response.status + response.statusText);
          })
      };
    // ==================================================
    
//3 ==================================================
    this.loadModelStructure = function (modelName) {

        //console.log("Get Model Structure");
        var getModelStructurePromise = ApiService.getModelStructure(modelName);
        getModelStructurePromise.then(
        function successCallback(response) {
            scope.sample = response.data;
            //console.log(scope.sample);
            scope.HeaderColumnName = scope.sample.headerColumn;
            scope.PrimaryKeyName = scope.sample.primaryKey;
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
    };
// ==================================================
    
  //==================================================
  // bind html eg
    this.bindSomeCrazyHTML = function () {

        angular.forEach(scope.modelData, function (value, key) {
            //var oneModelObject = value;
            value.modelPk = value[scope.PrimaryKeyName];
            value.modelHeader = value[scope.HeaderColumnName];
            //console.log(value.modelPk);
            //console.log(scope.examplePk+scope.exampleHeaderColumn);
        })

    };
  //==================================================
    
    
    
  //==================================================
    this.resetFlag = function () {
        this.showCreateAlert = false;
    };
  //==================================================
    
  
  //==================================================
  // getSingle
    this.getModel = function (modelID) {

        scope.newModel = {};
        var getOnePromise=ApiService.getOne(modelID);
        getOnePromise.then(
        function successCallback(response) {
            scope.model = response.data;
            scope.HeaderColumn = scope.model[scope.HeaderColumnName];
            scope.PrimaryKey=scope.model[scope.PrimaryKeyName];
            if(GlobalStuff.globalModelName==="Student")
            	{
	            	scope.model.EnrollmentDate = new Date(scope.model['EnrollmentDate']);
	                ////console.log(scope.model.EnrollmentDate);
            	}
            //console.log(scope.model);
            //console.log(scope.sample.columns);
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
        

    };
  //==================================================
    
    
  //==================================================
  // Create
    this.createModelItem = function (modelObject) {

        //console.log(modelObject);
        var createPromise=ApiService.create(modelObject);
        createPromise.then(
        function successCallback() {
            //console.log("Created");
            scope.showCreateAlert = true;
            scope.loadGrid(GlobalStuff.globalModelName);  
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
        

    };
  //==================================================
    
    
  //==================================================
  // Update
    this.editModelItem = function (modelID, modelObject) {

        var updatePromise=ApiService.update(modelID,modelObject);
        updatePromise.then(
        function successCallback() {
            //console.log("Updated");
            scope.showCreateAlert = true;
            scope.loadGrid(GlobalStuff.globalModelName);
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
        scope.showCreateAlert = true;
    };
  //==================================================
    
  //==================================================
  // Delete
    this.deleteModelItem = function (modelID) {

        var deletePromise=ApiService.delete(modelID);
        deletePromise.then(
        function successCallback(response) {
            //console.log("Deleted");
            scope.showCreateAlert = true;
            scope.loadGrid(GlobalStuff.globalModelName);
        },
        function errorCallback(response) {
            //console.log(response.status + response.statusText);
        })
        
    };
  //==================================================
    
}])


})();