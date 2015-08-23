/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var homeApp = angular.module('homeApp', []);
homeApp.controller('AppController', AppController);
    
homeApp.directive("submodule", function ()
{
    return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      element.bind('click', function()
      {
           scope.$apply(attrs.submodule);
      });
          
    },
  };
});

homeApp.directive("media", function ()
{
    return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      element.bind('click', function()
      {     
          scope.$apply(attrs.media);
            
      });
          
    }
  };
});
    
function AppController($scope, $http)
{
    $scope.menuControllerData = {hoverCategoryName: "", hoverSubCategoryName: "",
        selectedCategoryName: "", selectedSubCategoryName: ""};
    $http({method: 'GET', url: "data/menu_data.json"}).
            success(function (data, status) {
                $scope.menuData = data;
            }).
            error(function (data, status) {
                console.log('Error');
                console.log(data || "Request failed");
            }); 
    $scope.data = {message:""};
    $scope.hoverInCategory = function(categoryName)
    {
        $scope.menuControllerData.hoverCategoryName = categoryName;
    };
    $scope.hoverOutCategory = function()
    {
         $scope.menuControllerData.hoverCategoryName = "";
    };
    
    $scope.selectCategory = function(categoryName)
    {
        $scope.menuControllerData.selectedCategoryName = categoryName;
        $scope.loadContent();
    };
    $scope.hoverInSubCategory = function(subCategoryName)
    {
        $scope.menuControllerData.hoverSubCategoryName = subCategoryName;
    };
    $scope.hoverOutSubCategory = function()
    {
         $scope.menuControllerData.hoverSubCategoryName = "";
    };
    
    $scope.selectSubCategory = function(subCategoryName)
    {
        $scope.menuControllerData.selectedSubCategoryName = subCategoryName;
         $scope.loadContent();
    };
    
    //Content
     $scope.contentData={};
     $scope.loadContent = function ()
    {
        if ($scope.menuControllerData.selectedCategoryName !== ""
                && $scope.menuControllerData.selectedSubCategoryName !== ""
                )
        {
            
            if ($scope.menuData
                    && $scope.menuData.categories[$scope.menuControllerData.selectedCategoryName]
                    && $scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory[$scope.menuControllerData.selectedSubCategoryName]
                    && $scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory[$scope.menuControllerData.selectedSubCategoryName].dataFile
                    )
            {
                var dataFile = $scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory[$scope.menuControllerData.selectedSubCategoryName].dataFile;                
                $http({method: 'GET', url: dataFile}).
                        success(function (data, status) {
                            $scope.contentData = data;
                        }).
                        error(function (data, status) {
                            console.log('Error');
                            console.log(data || "Request failed");
                        });
            }
            else
            {
                $scope.contentData={};
            }
        }
        else
        {
           $scope.contentData={};
        }
    };
    
    
    $scope.controllerData={
        submoduleIndex:-1,
        mediaIndex:0} ;
    $scope.setSubModuleIndex = function(submoduleIndex)
    {
        $scope.controllerData.submoduleIndex = submoduleIndex;
        $scope.controllerData.mediaIndex = 0;
    }
    $scope.setMediaIndex = function(mediaIndex)
    {
        $scope.controllerData.mediaIndex = mediaIndex;
    }
    
    
}
