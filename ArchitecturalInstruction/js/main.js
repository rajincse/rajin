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
