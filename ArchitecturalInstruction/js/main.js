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
    init();
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
                $scope.controllerData.submoduleIndex = -1;
                $scope.controllerData.mediaIndex = 0;
                var dataFile = $scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory[$scope.menuControllerData.selectedSubCategoryName].dataFile;                
                console.log(dataFile);
                $http({method: 'GET', url: dataFile}).
                        success(function (data, status) {
                            $scope.contentData = data;
                        }).
                        error(function (data, status) {
                            console.log('Error '+status );
                            console.log(data+" "+status || "Request failed");
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
        init('subModule');
    }
    $scope.setMediaIndex = function(mediaIndex)
    {
        $scope.controllerData.mediaIndex = mediaIndex;
        init('media');
    }
    function init(param)
    {
        if(param)
        {
            
            if($scope.contentData.moduleName)
            {
                var currentSubModule = $scope.contentData.submodule[ $scope.controllerData.submoduleIndex];
                var currentMedia = currentSubModule.mediaList[ $scope.controllerData.mediaIndex];
                if(currentSubModule && currentSubModule && currentMedia.aoiData)
                {
                    var command =['removeAllElem'];
                    var bigMediaImage = angular.element(query('.container div.media-image-big img'))[0].getBoundingClientRect();
                    var rect={x:bigMediaImage.left|0, y:bigMediaImage.top|0, width:bigMediaImage.width|0, height:bigMediaImage.height|0}
                    var scaleX = rect.width/ currentMedia.aoiData.width;
                    var scaleY = rect.height / currentMedia.aoiData.height;
                    var bigMediaName = currentMedia.aoiData.imageName;
                    command.push('addElem_'+bigMediaName+'_'+rect.x+'_'+rect.y+'_'+rect.width+'_'+rect.height);
                    for(var i=0;i<currentMedia.aoiData.aoiItemList.length;i++)
                    {
                        var aoi = currentMedia.aoiData.aoiItemList[i];
                        command.push('addElem_'+aoi.name+'_'+((aoi.x * scaleX+bigMediaImage.left)|0)+'_'+((aoi.y*scaleY+bigMediaImage.top)|0)
                                +'_'+((aoi.width*scaleX)|0)+'_'+((aoi.height*scaleY)|0));
                    }
                    instrument.sendCommands(command);
                }
                
            }
        }
        else
        {
            var rect = document.getElementsByTagName('body')[0].getBoundingClientRect();
            instrument.init(rect.width, rect.height);
            console.log('init');
        }
    }
    function query(command)
    {
        return document.querySelector(command);
    }
    
    
}
