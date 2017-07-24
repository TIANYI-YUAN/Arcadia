
var arcadia = {
    rpcAddr:"ArcadiaRPC",
    ifFocus:[],
    mapFiles : [],
    mapShared : [],
    mapStarred : [],
    mapBin : [],
    mapPhotos : [],
    currentCate : null,
    typeList:["docx","exe","png","jpg","pdf","txt","xlsx","zip","pptx"]
//     showkey:function(event,inputName){
//     console.log(inputName)
//     console.log(event);
//     console.log("123") 
//     }
};

arcadia.test = function(){
    console.log("arcadia!!")
};

arcadia.setCookie=function(){
    document.cookie="username=Vincent";
}();
arcadia.cookie = document.cookie;


var url = function() {
        return arcadia.rpcAddr;
};

//memory for zjmz.com
arcadia.rpc = function(methodName) {
    
        return $.ajax({
            type: 'POST',
            url: url.apply(null, arguments),
            data: {
                methodName: methodName,
                args: JSON.stringify(Array.prototype.slice.call(arguments, 1)),
                cookie: arcadia.cookie
            },
            // timeout: 12000,
            dataType: 'json'
        }).done(function(data, textStatus, jqXHR) {
            // var cookie = jqXHR.getResponseHeader('Zjmz-Cookie');
            // if (cookie && cookie != '') {
            //     zjmz.cookie = cookie;
            //     store.set('cookie', zjmz.cookie);
            //     zjmz.cookieObject = $.parseJSON(zjmz.cookie);
            // }
        }).fail(function(textStatus,jqXHR){
            console.log('後端返回失敗'+ methodName);
        });
    };

 arcadia.autoEnter =function(event,inputName,buttonName,extraIf,id){
    asc = event.keyCode;
    if(extraIf){
        if(asc==13 && arcadia.ifFocus[id]==1 && extraIf()){          
            $(buttonName).click();   
        }
    }else{
        if(asc==13 && arcadia.ifFocus[id]==1){
            $(buttonName).click();
        }
    };
 };

arcadia.bindInputButton = function(inputName,buttonName,extraIf){
     $("#passwdInput").focus(function(){
        arcadia.ifFocus[arcadia.ifFocus.length] = 1;
     });
     $("#passwdInput").blur(function(){
        arcadia.ifFocus[arcadia.ifFocus.length] = 0;
     });
 window.addEventListener("keypress",function(){arcadia.autoEnter(event,inputName,buttonName,extraIf,arcadia.ifFocus.length-1)},false);

};


arcadia.urlArges = function() {
        var args = {};
        var query = location.search.substring(1);

        var pairs = query.split("&");
        for (i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf("=");
            if (pos == -1) continue;
            var name = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            value = decodeURIComponent(value);
            args[name] = value;
        }
        return args;
    }

arcadia.fileModelShow = function(fileMap,domId,category){
        $("#fileFrame").html("");
        var windowWidth = $(window).width();
        var availableWidth = windowWidth-330-50;
        var mapSize = fileMap.files.length;

        // arcadia.mapFiles = fileMap.files;
           
        switch(category){
            case "files":
                    arcadia.mapFiles = [];
                    for(var i = 0;i<mapSize;i++){
                        if(fileMap.files[i].deleted==0){
                            arcadia.mapFiles.push(fileMap.files[i]);                  
                        }
                    }
                    map=arcadia.mapFiles;
                    break;
            case "shared":
                // if(arcadia.mapShared.length<=0){
                    arcadia.mapShared = [];
                    for(var i = 0;i<mapSize;i++){
                        if(fileMap.files[i].shared==1){
                            arcadia.mapShared.push(fileMap.files[i]);                            
                        }
                    }
                //     console.log("new mapShared");
                // } 
                    map=arcadia.mapShared;
                    break;
            case "starred":
                // if(arcadia.mapStarred.length<=0){
                    arcadia.mapStarred = [];
                    for(var i = 0;i<mapSize;i++){
                        if(fileMap.files[i].starred==1){
                            arcadia.mapStarred.push(fileMap.files[i]);
                        }
                    }
                //     console.log("new mapStarred");
                // } 
                    map=arcadia.mapStarred;
                    break;
            case "photos":
                // if(arcadia.mapPhotos.length<=0){
                    arcadia.mapPhotos = [];
                    for(var i = 0;i<mapSize;i++){
                        if(fileMap.files[i].photo==1){
                            arcadia.mapPhotos.push(fileMap.files[i]);
                        }
                    }
                //     console.log("new mapPhotos");
                // } 
                    map=arcadia.mapPhotos;
                    break;
            case "bin":
                // if(arcadia.mapBin.length<=0){
                    arcadia.mapBin =[];
                    for(var i = 0;i<mapSize;i++){
                        if(fileMap.files[i].deleted==1){
                            arcadia.mapBin.push(fileMap.files[i]);
                        }
                    }
                //     console.log("new mapBin");
                // } 
                    map=arcadia.mapBin;
                    break;
            default:
                    map=arcadia.mapBin;
                    break;
        }



        var lineCount = Math.floor(availableWidth/190);
        if(lineCount<=0){
            lineCount = 1;
        }
        var lineCountBuffer = lineCount;
        
        var x = 1;
        var y = 1;
        for(var i = 0 ; i < map.length; i++){
            
            map[i].x = x;
            map[i].y = y;
            lineCount--;
            if(lineCount==0){
                x = 1;
                y++;
                lineCount = lineCountBuffer;
            }else{
                x++;       
            }
        
            var name = map[i].filename;
            
            if(name.length>13){
                name = name.substring(0,13) + ".."
            }
            map[i].fileType = arcadia.checkFileType(map[i].filename);
            $("#fileFrame").append("<div id='"+domId+i
            +"' class='file'><div class='filemask'></div><div class='moreInfo' id='dl"+i+"'>"+ 
            "Download <span class='ion-ios-cloud-download-outline' style='font-size:18px;'>"+
            "</span></div>"+
            "<div class='delete ion-ios-close' id='del"+i+"'></div>"+
            "<div class='filePic'><img class='fPic' id='fPicNum"+i+"' /></div><div class='fileName' id ='nameNum"+i+"'>"+
            name+"</div></div>");
            if(arcadia.checkIfinTypeList(map[i].fileType)==1){
                $("#fPicNum"+i).attr("src","source/icons/"+map[i].fileType[0]+".png");
            }else{
                $("#fPicNum"+i).attr("src","source/icons/file.png");
            }
            $("#nameNum"+ i).attr("title",map[i].filename);
        }
        arcadia.bindFileClick();
      
}

arcadia.selectCategory =function(){
    var arges = arcadia.urlArges();     
    var id = document.location.hash;
    $(".navC_selected").attr("class","navC");               
    $(id).attr("class","navC_selected");
    $("#uploadButton").hide();
    $("#uploadButtonP").hide();
    if(id=="#photos"){
        $("#uploadButtonP").show();
    }else if(id=="#files"){
        
        $("#uploadButton").show();
    }else{
        // null
    } 
     
}


arcadia.bindFileClick = function(){
    currentChoseFile = null;
    $(".file").click(function(e){
        // console.log(e);
        if(currentChoseFile!= e.currentTarget.id){
            // console.log(currentChoseFile);
            if(currentChoseFile!=null){
                $("#dl"+currentChoseFile.substring(5)).animate({bottom: "10px"},200,"linear",null);
                $("#del"+currentChoseFile.substring(5)).animate({opacity: "0"},200,"linear",null);
                $("#del"+currentChoseFile.substring(5)).hide();
            }
            currentChoseFile = e.currentTarget.id;
            var id = e.currentTarget.id;
            $("#del"+id.substring(5)).show();
            $("#dl"+id.substring(5)).animate({bottom: "50px"},200,"linear",null);
            $("#del"+id.substring(5)).animate({opacity: "1"},200,"linear",null);
            files.files[id.substring(5)].selected = 1;
            
    }

    });
    $(document).click(function(e){
        //click out -- hide download 
        if(e.target.className !="filemask" && e.target.className !="filePic"&& e.target.className !="fileName"&& e.target.className !="fPic"&& e.target.className !="moreInfo"&&e.target.className !="ion-ios-cloud-download-outline" ){
            if(currentChoseFile!=null){
                $("#dl"+currentChoseFile.substring(5)).animate({bottom: "10px"},200,"linear",null);
                $("#del"+currentChoseFile.substring(5)).animate({opacity: "0"},200,"linear",null);
                $("#del"+currentChoseFile.substring(5)).hide();
                currentChoseFile=null;
            }
        }
     
    });

    $(".moreInfo").click(function(e){
        //绑定下载按钮
        var id = e.currentTarget.id.substring(2);
        arcadia.downloadFile(files.files[id].filename);
    });
    
    $('.delete').click(function(e){
        //绑定删除按钮
        console.log(e.target.id.substring(3));
        console.log(files.files[e.target.id.substring(3)].sourcecode);
        // arcadia.rpc("deleteFile",files.files[e.target.id.substring(3)].sourcecode).done(function(data){
        //     console.log(data);
        //     arcadia.refresh();
        // }).fail(function(){
        //     console.log("delete file failed.(main.js)");
        // });
        arcadia.rpc("moveFileToBin",files.files[e.target.id.substring(3)].sourcecode).done(function(data){
            console.log("moveFileToBin成功"); 
            arcadia.refresh();            
        }).fail(function(){
            console.log("moveFileToBin失败"); 
            
        }); 
    });
}

arcadia.refresh = function(){
    arcadia.rpc("getFileDetails",1).done(function(data){                  
        files = data;             
        arcadia.fileModelShow(files,"files",arcadia.currentCate);     
    }).fail(function(){
        console.log("getFileDetails失败"); 
    });
}

arcadia.downloadFile = function(fileName){
    var a = document.createElement('a');
    var filename = fileName;
    a.href = "/s/"+filename;
    a.download = filename;
    a.click();
}



arcadia.uploadFile = function(domId,creatorId,ifPhoto){

    var formData = new FormData($(domId)[0]);

    // for (var pair of formData.entries()) {
    // console.log(pair[0]+ ', ' + pair[1]); 
    // }
    formData.append("creatorId",creatorId);
    formData.append("ifPhoto",ifPhoto);
    return $.ajax({
    url: "/UploadServlet",
    type: 'POST',
    cache: false,
    data: formData,
    processData: false,
    contentType: false,
    dataType: 'json'
}).done(function(data, textStatus, jqXHR) {

    }).fail(function(res) {
    console.log(res);    
    });  
}


arcadia.checkFileType = function(fileName){
    //检查扩展名
    var r = /\.[^\.]+$/.exec(fileName);
    r[0]=r[0].substring(1);
    return r;

}
arcadia.checkIfinTypeList = function(type){
    for(var i=0;i<arcadia.typeList.length;i++){
        if(type==arcadia.typeList[i]){
            return 1;
        }
      
    }
    return 0;
}


    
