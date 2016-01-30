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
homeApp.directive("scroll", function () {
        return function(scope, element, attrs) {
            angular.element(element).bind("scroll", function() {
                scope.$apply(attrs.scroll);
            });
        };
    });

homeApp.directive('dataloadingdelay', ['$timeout', function ($timeout) {
    return {
        restrict: 'A',
        link: function ($scope, element, attrs) {
            $scope.$on('dataloaded', function () {
                $timeout(function () { 
                    
                    $scope.postRender();
                    
                }, attrs.dataloadingdelay, false);
            })
        }
    };
}]);
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
       console.log('select category');
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
   
    $scope.$watch("contentData", function(newValue, oldValue) {
       $scope.$broadcast('dataloaded');

    });
    $scope.postRender = function(newValue, oldValue)
    {
        $scope.reInstrument('post render');
        $scope.setScrollToTop();
    };
    $scope.controllerData={
        submoduleIndex:-1,
        mediaIndex:0} ;
    $scope.setSubModuleIndex = function(submoduleIndex)
    {
        $scope.controllerData.submoduleIndex = submoduleIndex;
        $scope.controllerData.mediaIndex = 0;
        $scope.$broadcast('dataloaded');
    }
    $scope.setMediaIndex = function(mediaIndex)
    {
        $scope.controllerData.mediaIndex = mediaIndex;
        $scope.$broadcast('dataloaded');
    }
    $scope.instrumentationCheckChanged = function(val)
    {
        instrument.enabled =val;
    }
    $scope.scrollEvent= function ()
    {
        this.reInstrument('scroll');
    }
    $scope.setScrollToTop = function()
    {
        window.scrollTo(0,0);
        var subModuleDescriptionContainer =$('.container div.media-list-summary div.submodule-description')[0];
        if(subModuleDescriptionContainer)
        {
            subModuleDescriptionContainer.scrollTop=0;
        }
    }
    
    $scope.reInstrument = function(param)
    {
        console.log('re-instrument :'+param);
        if(param)
        {
             var command =['removeAllElem'];
            //reset scroll
            var windowScroll = $(window);
            command.push('translate_-'+windowScroll.scrollLeft()+'_-'+windowScroll.scrollTop());
            //homelogo
            var homeLogoText = $('div.menubar div.home-logo div.large-text')[0].getBoundingClientRect();
            var homeLogo = $('div.menubar div.home-logo div a img')[0].getBoundingClientRect();
            command.push('addElem_homelogo:text@g0h0_'+getRectangleString(homeLogoText));
            command.push('addProperty_homelogo:text@g0h0_type=text');
            command.push('addElem_homelogo:image@g0i0_'+getRectangleString(homeLogo));
            command.push('addProperty_homelogo:image@g0i0_type=image');
            

            
            if($scope.menuControllerData.selectedCategoryName)
            {
                 //categories
                var categoryButtons = $('div.menubar div.button-list-main a img');
                var keys = Object.keys($scope.menuData.categories);
                for(var i=0;i<categoryButtons.length;i++)
                {
                    var rect = categoryButtons[i].getBoundingClientRect();
                    var module =$scope.menuData.categories[keys[i]];
                    var id =module.name+':button@'+module.id+'t0';
                    command.push('addElem_'+id+'_'+getRectangleString(rect));
                    command.push('addProperty_'+id+'_type=button');
                }
                //subcategories
                var moduleButtons = $('div.menubar div.button-list-small a img');
                var moduleKeys = Object.keys($scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory);
                for(var i=0;i<moduleButtons.length;i++)
                {
                    var rect = moduleButtons[i].getBoundingClientRect();
                    var id = moduleKeys[i]+':button@'+$scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].subCategory[moduleKeys[i]].id+'t0';
                    command.push('addElem_'+id+'_'+getRectangleString(rect));
                    command.push('addProperty_'+id+'_type=button');
                }
                //category text
                var categoryText = $('.container .welcome-message div span');
                for(var i=0;i<categoryText.length;i++)
                {
                    var word = $(categoryText[i]).text().trim();
                    var id = word+':text@'+$scope.menuData.categories[$scope.menuControllerData.selectedCategoryName].id+$(categoryText[i]).attr('id');
                    var rect =getRectangleString(categoryText[i].getBoundingClientRect());
                    command.push('addElem_'+id+'_'+getRectangleString(categoryText[i].getBoundingClientRect()));
                    command.push('addProperty_'+id+'_type=text');
                }
            }
            
            if($scope.contentData.moduleName)
            {
                
                //Submodule Images
                   var submodules = $('.container div.submodule-list div img'); 
                   for(var i=0;i<submodules.length;i++)
                   {
                       var rect = submodules[i].getBoundingClientRect();
                       var id = $scope.contentData.submodule[i].subModuleName+':button@'+$scope.contentData.id+'s'+i+'t0';
                       command.push('addElem_'+id+'_'+getRectangleString(rect));
                       command.push('addProperty_'+id+'_type=button');
                   }
                if($scope.controllerData.submoduleIndex <0)
                {
                    var moduleDescription = $('.container .module-welcome-message div span');
                    for(var i=0;i<moduleDescription.length;i++)
                    {
                        var word = $(moduleDescription[i]).text().trim();
                        var id = word+':text@'+$scope.contentData.id+$(moduleDescription[i]).attr('id');
                        var rect =getRectangleString(moduleDescription[i].getBoundingClientRect());
                        command.push('addElem_'+id+'_'+getRectangleString(moduleDescription[i].getBoundingClientRect()));
                        command.push('addProperty_'+id+'_type=text');
                    }
                }
                else
                {
                    var currentSubModule = $scope.contentData.submodule[ $scope.controllerData.submoduleIndex];
                    var currentMedia = currentSubModule.mediaList[ $scope.controllerData.mediaIndex];
                    
                   
                    if(currentSubModule)
                    {
                        //Texts
                        var moduleTitle = $('.container div.media-list-summary div.module-title')[0].getBoundingClientRect();
                        var subModuleTitle = $('.container div.media-list-summary div.submodule-title')[0].getBoundingClientRect();
                        var submoduleId = $scope.contentData.id+'s'+$scope.controllerData.submoduleIndex;
                        
                        command.push('addElem_'+$scope.contentData.moduleName+':text@'+$scope.contentData.id+'h0_'+getRectangleString(moduleTitle));
                        command.push('addProperty_'+$scope.contentData.moduleName+':text@'+$scope.contentData.id+'h0_type=text');
                        command.push('addElem_'+currentSubModule.subModuleName+':text@'+submoduleId+'h0_'+getRectangleString(subModuleTitle));
                        command.push('addProperty_'+currentSubModule.subModuleName+':text@'+submoduleId+'h0_type=text');
                        
                        var subModuleDescriptionContainer =$('.container div.media-list-summary div.submodule-description')[0];
                        var containerRect = subModuleDescriptionContainer.getBoundingClientRect();
                        var subModuleDescriptionTexts  = $('.container div.media-list-summary div.submodule-description div span');
                        
                        for(var i=0;i<subModuleDescriptionTexts.length;i++)
                        {
                            var word = $(subModuleDescriptionTexts[i]).text().trim();
                            var wordRect = subModuleDescriptionTexts[i].getBoundingClientRect();
                            if(isInside(containerRect, wordRect))
                            {
                                var rect =getRectangleString(wordRect);
                                var id = word+':text@'+submoduleId+$(subModuleDescriptionTexts[i]).attr('id');
                                command.push('addElem_'+id+'_'+getRectangleString(subModuleDescriptionTexts[i].getBoundingClientRect()));
                                command.push('addProperty_'+id+'_type=text');     
                            }
                            
                        }
                        
                        //Thumbnail List
                        var mediaThumbnails = $('.container div.media-list-summary div.media-image-thumbnail');
                        for(var i=0;i<mediaThumbnails.length;i++)
                        {
                            var rect = mediaThumbnails[i].getBoundingClientRect();
                            var imageName =getCleanString(currentSubModule.mediaList[i].mediaImage);
                            var id = imageName+':button@'+submoduleId+'t'+i;
                            command.push('addElem_'+id+'_'+getRectangleString(rect));
                            command.push('addProperty_'+id+'_type=button');
                        }

                        //Additional Media
                        var additionalMedia = $('.container div.media-list-summary div.media-additional img');
                        for(var i=0;i<additionalMedia.length;i++)
                        {
                            var rect = additionalMedia[i].getBoundingClientRect();
                            var imageName = getCleanString(currentMedia.additionalMedia[i]);
                            var id = imageName+':image@'+submoduleId+'ai'+i;
                            command.push('addElem_'+id+'_'+getRectangleString(rect));
                            command.push('addProperty_'+id+'_type=image');
                        }
                        
                        //bigMediaImage
                        var bigMediaImage = $('.container div.media-image-big img')[0].getBoundingClientRect();
                        var rect={left:bigMediaImage.left|0, top:bigMediaImage.top|0, width:bigMediaImage.width|0, height:bigMediaImage.height|0}
                        var bigMediaId = submoduleId+'i'+$scope.controllerData.mediaIndex;
                        if(currentMedia.aoiData)
                        {
                            var scaleX = rect.width/ currentMedia.aoiData.width;
                            var scaleY = rect.height / currentMedia.aoiData.height;
                            var bigMediaName = getCleanString(currentMedia.aoiData.imageName).trim();
                            
                            var id = bigMediaName+':image@'+bigMediaId;
                            command.push('addElem_'+id+'_'+getRectangleString(rect));
                            command.push('addProperty_'+id+'_type=image');
                            for(var i=0;i<currentMedia.aoiData.aoiItemList.length;i++)
                            {
                                var aoi = currentMedia.aoiData.aoiItemList[i];
                                var aoiId = aoi.name+':aoi@'+bigMediaId+'a'+i;
                                command.push('addElem_'+aoiId+'_'+((aoi.x * scaleX+bigMediaImage.left)|0)+'_'+((aoi.y*scaleY+bigMediaImage.top)|0)
                                        +'_'+((aoi.width*scaleX)|0)+'_'+((aoi.height*scaleY)|0));
                                command.push('addProperty_'+aoiId+'_type=aoi');
                            }
                        }
                        else
                        {
                            var imageName = getCleanString(currentMedia.mediaImage);
                            var id = imageName+':image@'+bigMediaId;
                            command.push('addElem_'+id+'_'+getRectangleString(rect));
                            command.push('addProperty_'+id+'_type=image');
                        }
                    }
                }
                
            }       

                
            instrument.sendCommands(command);
            
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
        return str.replace(new RegExp('_','g'),' ');
    }
    
    function getSlashFreeString(str)
    {
        var slashIndex = str.lastIndexOf('/');
        if(slashIndex> -1)
        {
            return str.substring(slashIndex+1, str.length);
        }
        else
        {
            return str;
        }
    }
    
    function getExtensionFreeString(str)
    {
        var extensionString = str.lastIndexOf('.');
         if(extensionString> -1)
        {
            return str.substring(0, extensionString);
        }
        else
        {
            return str;
        }
    }
    function getCleanString(str)
    {
        str = getUnderscoreFreeString(str);
        str = getSlashFreeString(str);
        str = getExtensionFreeString(str);
        return str;
    }
    function isInside(containerRect, componentRect)
    {
        if(componentRect.height > 0 
                && componentRect.bottom >= containerRect.top 
                && componentRect.top <= containerRect.bottom)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
