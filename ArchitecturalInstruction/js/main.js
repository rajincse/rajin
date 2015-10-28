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
           scope.reInstrument('submodule index');
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
          scope.reInstrument('media');  
      });
          
    }
  };
});
    
function AppController($scope, $http)
{
    
    $scope.menuControllerData = {hoverCategoryName: "", hoverSubCategoryName: "",
        selectedCategoryName: "", selectedSubCategoryName: "", instrumentationCheckChanged:false};
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
        $scope.reInstrument('selectCategory');
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
    }
    $scope.setMediaIndex = function(mediaIndex)
    {
        $scope.controllerData.mediaIndex = mediaIndex;
    }
    $scope.instrumentationCheckChanged = function(val)
    {
        instrument.enabled =val;
    }
    $scope.reInstrument = function(param)
    {
        console.log('re-instrument :'+param);
        if(param)
        {
            if($scope.contentData.moduleName)
            {
               
                 var command =['removeAllElem'];
                 
                 //homelogo
                 var homeLogoText = $('div.menubar div.home-logo div.large-text')[0].getBoundingClientRect();
                 var homeLogo = $('div.menubar div.home-logo div a img')[0].getBoundingClientRect();
                 command.push('addElem_homelogoText_'+getRectangleString(homeLogoText));
                 command.push('addElem_homelogo_'+getRectangleString(homeLogo));

                 //categories
                 var categoryButtons = $('div.menubar div.button-list-main a img');
                 var keys = Object.keys($scope.menuData.categories);
                 for(var i=0;i<categoryButtons.length;i++)
                 {
                     var rect = categoryButtons[i].getBoundingClientRect();
                     command.push('addElem_'+$scope.menuData.categories[keys[i]].name+'_'+getRectangleString(rect));
                 }
                 
                 //subcategories
                 var subCategoryButtons = $('div.menubar div.button-list-small a img');
                 for(var i=0;i<subCategoryButtons.length;i++)
                 {
                     var rect = subCategoryButtons[i].getBoundingClientRect();
                     command.push('addElem_subcategory_'+getRectangleString(rect));
                 }

                if($scope.contentData.moduleName)
                {
                    var currentSubModule = $scope.contentData.submodule[ $scope.controllerData.submoduleIndex];
                    var currentMedia = currentSubModule.mediaList[ $scope.controllerData.mediaIndex];

                    //Submodule Images
                    var submodules = $('.container div.submodule-list div img'); 
                    for(var i=0;i<submodules.length;i++)
                    {
                        var rect = submodules[i].getBoundingClientRect();
                        command.push('addElem_'+$scope.contentData.submodule[i].subModuleName+':submoduleThumbnail_'+getRectangleString(rect));
                    }
                    if(currentSubModule)
                    {
                        //Texts
                        var moduleTitle = $('.container div.media-list-summary div.module-title')[0].getBoundingClientRect();
                        var subModuleTitle = $('.container div.media-list-summary div.submodule-title')[0].getBoundingClientRect();
                        var subModuleDescription  = $('.container div.media-list-summary div.submodule-description')[0].getBoundingClientRect();

                        command.push('addElem_'+$scope.contentData.moduleName+'_'+getRectangleString(moduleTitle));
                        command.push('addElem_'+currentSubModule.subModuleName+'_'+getRectangleString(subModuleTitle));
                        command.push('addElem_'+currentSubModule.subModuleName+':desc_'+getRectangleString(subModuleDescription));

                        //Thumbnail List
                        var mediaThumbnails = $('.container div.media-list-summary div.media-image-thumbnail');
                        for(var i=0;i<mediaThumbnails.length;i++)
                        {
                            var rect = mediaThumbnails[i].getBoundingClientRect();
                            command.push('addElem_'+getUnderscoreFreeString(currentSubModule.mediaList[i].mediaImage)+':mediaThumbnail_'+getRectangleString(rect));
                        }

                        //Additional Media
                        var additionalMedia = $('.container div.media-list-summary div.media-additional img');
                        for(var i=0;i<additionalMedia.length;i++)
                        {
                            var rect = additionalMedia[i].getBoundingClientRect();
                            command.push('addElem_'+currentMedia.additionalMedia[i]+':additionalMedia_'+getRectangleString(rect));
                        }
                        
                        //bigMediaImage
                        var bigMediaImage = $('.container div.media-image-big img')[0].getBoundingClientRect();
                        var rect={left:bigMediaImage.left|0, top:bigMediaImage.top|0, width:bigMediaImage.width|0, height:bigMediaImage.height|0}
                        if(currentMedia.aoiData)
                        {
                            var scaleX = rect.width/ currentMedia.aoiData.width;
                            var scaleY = rect.height / currentMedia.aoiData.height;
                            var bigMediaName = getUnderscoreFreeString(currentMedia.aoiData.imageName);
                            command.push('addElem_'+bigMediaName+'_'+getRectangleString(rect));
                            for(var i=0;i<currentMedia.aoiData.aoiItemList.length;i++)
                            {
                                var aoi = currentMedia.aoiData.aoiItemList[i];
                                command.push('addElem_'+bigMediaName+':'+aoi.name+'_'+((aoi.x * scaleX+bigMediaImage.left)|0)+'_'+((aoi.y*scaleY+bigMediaImage.top)|0)
                                        +'_'+((aoi.width*scaleX)|0)+'_'+((aoi.height*scaleY)|0));
                            }
                        }
                        else
                        {
                            command.push('addElem_'+getUnderscoreFreeString(currentMedia.mediaImage)+'_'+getRectangleString(rect));
                        }
                    }
                    

                }
                instrument.sendCommands(command);
            }
            
        }
        else
        {
            var rect = document.getElementsByTagName('body')[0].getBoundingClientRect();
            instrument.init(rect.width, rect.height);
        }
       
    }
    $scope.reInstrument();
    function query(command)
    {
        return document.querySelector(command);
    }
    function getRectangleString(rect)
    {
        return (rect.left|0)+'_'+(rect.top|0)+'_'+(rect.width|0)+'_'+(rect.height|0);
    }
    
    function getUnderscoreFreeString(str)
    {
        return str.replace(new RegExp('_','g'),'');
    }
}
