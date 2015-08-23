/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var currentButton='';

var mainButtonNames =['Building Form', 'Building Envelopes',
             'Structure', 'Climate Control','Renewable Energy', 'Lighting', 'Landscape' 
         ];
var smallButtonNames =
{
    'Building Envelopes':['Envelope Concepts', 'Wall Systems', 'Climate Responsive Faccades']
            
};
var homeApp = angular.module('homeApp', []);
homeApp.controller('MenuBar', MenuBar);
var buildingEnvelopesApp = angular.module('buildingEnvelopes', []);
buildingEnvelopesApp.controller('MenuBar', MenuBar);
var climateResponsiveFaccades = angular.module('climateResponsiveFaccades', []);
climateResponsiveFaccades.controller('MenuBar', MenuBar);

    

    
function MenuBar($scope)
{
    $scope.data = {message:""};
    $scope.hoverInMain = function(buttonIndex)
    {
        $scope.data = {message:mainButtonNames[buttonIndex]};
    }
    $scope.hoverOutMain = function()
    {
         $scope.data = {message:""};
    };
    
    $scope.hoverInSmall = function(page, buttonIndex)
    {
        $scope.data2 = {message:smallButtonNames[page][buttonIndex]};
    }
    $scope.hoverOutSmall = function()
    {
         $scope.data2 = {message:""};
    };
    
}
climateResponsiveFaccades.directive("submodule", function ()
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

climateResponsiveFaccades.directive("media", function ()
{
    return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      element.bind('click', function()
      {     
          scope.$apply(attrs.media);
            
      });
          
    },
  };
});
    
climateResponsiveFaccades.controller('contentController', function ($scope, $http)
{
    $scope.controllerData={submoduleIndex:-1,mediaIndex:0} ;
    $scope.setSubModuleIndex = function(submoduleIndex)
    {
        $scope.controllerData.submoduleIndex = submoduleIndex;
    }
    $scope.setMediaIndex = function(mediaIndex)
    {
        $scope.controllerData.mediaIndex = mediaIndex;
    }
    $http({method: 'GET', url: "data/climate_responsive_facades.json"}).
            success(function (data, status) {
                $scope.contentData = data;
            }).
            error(function (data, status) {
                console.log('Error');
                console.log(data || "Request failed");
            }); 
});