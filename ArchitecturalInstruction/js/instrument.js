
var instrument =
{
        visWidth: 0, 
        visHeight: 0,
        url:"http://localhost:9999/apa/bla.txt?",
        init: function( visWidth, visHeight, url)
        {
            this.visWidth = visWidth;
            this.visHeight = visHeight;
            if(url)
            {
                this.url = url;   
            }
            
        },
        
        windowReshaped: function()
        {
            this.sendCommands(["window_" + screenX + "_" + screenY + "_" + Math.min(window.innerWidth,this.visWidth) + "_" + Math.min(window.innerHeight, this.visHeight)]);
        }, 
        sendCommands:function(c)
        {
            var s = this.url;
        
            for (var i=0; i<c.length; i++)
                    s+= "command=" + c[i];
            console.log('Sending command:'+s);
            var xhttp = new XMLHttpRequest();
            xhttp.open('GET', s, true);
            xhttp.send();
        }
        
}

    
window.onmousemove = function(e){


        var newscreenx = e.screenX - e.clientX - document.getElementsByTagName('body')[0].getBoundingClientRect().left ;
        var newscreeny = e.screenY - e.clientY - document.getElementsByTagName('body')[0].getBoundingClientRect().top ;;
        if (newscreenx != screenX || newscreeny != screenY){
                screenX = newscreenx; screenY = newscreeny;
                instrument.windowReshaped();
        }
        //sendCommands(["gaze_"+e.screenX + "_" + e.screenY]);
}
	
window.onblur = function(){
       instrument.sendCommands(["lostfocus"]);
}
	
window.onfocus = function(){
        instrument.sendCommands(["gainedfocus"]);
}