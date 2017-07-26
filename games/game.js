document.onload = function(){
    console.log("game start!")
    $(window).click(function (e) { 
        e.preventDefault();
        console.log("X位置："+e.clientX);
        console.log("Y位置："+e.clientY);
        
        bulletgo();
        bulletupdate(e.clientX,e.clientY);
    });


         windowHeight = $(window).height();
         windowWidth = $(window).width();
bulletX = 0;
bulletY = 0;
playerX = 0;
playerY = 0;
bulletflag = 0;

    $("#fileContainer").append("<div id='player' style='height:40px;width:30px;position:absolute;z-index:9999;left:0px;top:0px;'><img src='games/pl1.png'/></div>");
    window.addEventListener("keypress",function(){gameinput(event)},false);
    function bulletgo(){

        $("#fileContainer").append("<div id='bullet' style='height:10px;width:10px;position:absolute;z-index:9999;left:0px;top:0px;'><img src='games/bu.png'/></div>");
        $("#bullet").css("left",$("#player").offset().left);
        $("#bullet").css("top",$("#player").offset().top);
        
    };
    function bulletupdate(x,y){
        var speed = 30;
        var xD = x-playerX;
        var yD = y-playerY;
        radians = Math.atan2(xD,-yD);
        angle = radians *(180/Math.PI);
       
        if(xD>0 && yD>0){
            var prop = yD + xD;
            var xM = (xD/prop) * speed;
            var yM = (yD/prop) * speed;
            bulletX = xM;
            bulletY = yM;
        }else if(xD>0 && yD<0){
            var prop = -yD + xD;
            var xM = (xD/prop) * speed;
            var yM = (-yD/prop) * speed;
            bulletX = xM;
            bulletY = -yM;
        }else if(xD<0 && yD>0){
            var prop = yD - xD;
            var xM = (-xD/prop) * speed;
            var yM = (yD/prop) * speed;
            bulletX = -xM;
            bulletY = yM;
        }else if(xD<0 && yD<0){
            var prop = -yD - xD;
            var xM = (-xD/prop) * speed;
            var yM = (-yD/prop) * speed;
            bulletX = -xM;
            bulletY = -yM;
        }
        $("#bullet").css("transform","rotate("+angle+"deg)");
        console.log(xD);
        console.log(yD);
        
        console.log(bulletX);
        console.log(bulletY);
        bulletflag = 1;
    }
    function gameinput(e){
        console.log(e.key);
        console.log($("#player").offset().left);
        console.log($("#player").offset().top);
        
        switch(e.key){
            case "d":
                playerX += 10;
                break;
            case "s":
                playerY += 10;
                break;
            case "a":
                playerX -= 10;
                break;
            case "w":
                playerY -= 10;
                break;
        } 
    }

    function gameRender(){
        setInterval(function(){
            $("#player").css("left",playerX);
            $("#player").css("top",playerY);
            if(bulletflag==1){
            $("#bullet").css("left",$("#bullet").offset().left+bulletX);
            $("#bullet").css("top",$("#bullet").offset().top+bulletY);
            if($("#bullet").offset().left<0 ||$("#bullet").offset().left>windowWidth||$("#bullet").offset().top>windowHeight||$("#bullet").offset().top<0){
                $("#bullet").remove();
                bulletflag=0;
            }
            }
            
        },16);
    };
    gameRender();
}();