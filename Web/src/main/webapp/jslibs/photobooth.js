/**
    *
    * Photobooth.js version 0.7
    *
    * build Sat Dec 08 2012 04:51:51 GMT-0500 (EST)
    *
    * CSS
    */
window.addEventListener("load",function(){
    var s = document.createElement("style");
    s.innerHTML=".photobooth{position:relative;font:11px arial,sans-serif;overflow:hidden;user-select:none;-webkit-user-select:none;-moz-user-select:none;-o-user-select:none}.photobooth canvas{position:absolute;left:0;top:0}.photobooth .blind{position:absolute;left:0;top:0;opacity:0;width:100%;height:100%;background:#fff;z-index:1}.photobooth .blind.anim{transition:opacity 1500ms ease-out;-o-transition:opacity 1500ms ease-out;-moz-transition:opacity 1500ms ease-out;-webkit-transition:opacity 1500ms ease-out}.photobooth .warning{position:absolute;top:45%;background:#ffebeb;color:#cf0000;border:1px solid #cf0000;width:60%;left:50%;margin-left:-30%;display:none;padding:5px;z-index:10;text-align:center}.photobooth .warning span{text-decoration:underline;cursor:pointer;color:#333}.photobooth ul{width:30px;position:absolute;right:0;top:0;background:rgba( 0,0,0,.6 );height:190px;z-index:2;border-bottom-left-radius:5px}.photobooth ul li{width:30px;height:38px;background-repeat:no-repeat;background-position:center center;cursor:pointer;position:relative}.photobooth ul li:hover{background-color:#aaa}.photobooth ul li.selected{background-color:#ccc}.photobooth ul.noHSB{height:80px}.photobooth ul.noHSB li.hue,.photobooth ul.noHSB li.saturation,.photobooth ul.noHSB li.brightness{display:none}.photobooth ul li.hue{background-image:url(data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAAZAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQICAgICAgICAgICAwMDAwMDAwMDAwEBAQEBAQECAQECAgIBAgIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMD/8AAEQgACAAYAwERAAIRAQMRAf/EAHgAAQEAAAAAAAAAAAAAAAAAAAkIAQEAAwAAAAAAAAAAAAAAAAAKBggLEAAAAwQLAAAAAAAAAAAAAAAAMQZBAjQ4A3MEdMQFdQcICTkRAAEBBAcGBwAAAAAAAAAAABExAAEhElECMjMEBQlhwgNzFDgVNRY3CBgK/9oADAMBAAIRAxEAPwBGOKPmqmNdT5FD2YgarLO67OVueIqrxF2tI/1Kn0jjjKfFcJZEt+5BAUCAaKuw+ThT3vC0wbFof+U4Dnv3WGl8Pu47A8vecwabKy8ZRVNKFdF3dY72fztbVdFu67axelcfrPkYlPTutCW7qqYCkwDf/9k=)}.photobooth ul li.saturation{background-image:url(data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAAZAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQICAgICAgICAgICAwMDAwMDAwMDAwEBAQEBAQECAQECAgIBAgIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMD/8AAEQgACAAYAwERAAIRAQMRAf/EAGMAAAMAAAAAAAAAAAAAAAAAAAYICQEBAQEAAAAAAAAAAAAAAAAACAkKEAAABgMBAAAAAAAAAAAAAAAAwYIDMwZxAkQHEQABAgUFAAAAAAAAAAAAAAAAAQYxgQIyM3HBQgMH/9oADAMBAAIRAxEAPwAwo0rWdSFXHBYpnLZmWjVB/fLedIODu5Do81j1y2KE0CJlJA2uK5ZjtY2Kg//Z)}.photobooth ul li.brightness{background-image:url(data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAAZAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQICAgICAgICAgICAwMDAwMDAwMDAwEBAQEBAQECAQECAgIBAgIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMD/8AAEQgACAAYAwERAAIRAQMRAf/EAFcAAQAAAAAAAAAAAAAAAAAAAAoBAQAAAAAAAAAAAAAAAAAAAAAQAAAEBQUAAAAAAAAAAAAAAACxAwgBMXECBXJzBDQ1EQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwAcTWfR4GtIwC5mITxNUDgAYA0joY3aRKwB/9k=)}.photobooth ul li.crop{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAICAYAAADjoT9jAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAEFJREFUeNpi/A8EDAjACMT/qUgzMCJZwMhAXQA2l4VGhsPNZKKR4XBfMMG8QiPASDcf0MIX/2FxgCJARRoMAAIMAK49Iv4yTUj5AAAAAElFTkSuQmCC)}.photobooth ul li.trigger{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAASCAYAAABB7B6eAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAa9JREFUeNqc1M8rRFEUwPF5M4MhP8aPIiIS21lQk1Is5ceChZIdOytlI/+A7Ig/gGRhpYiNbKQsrBRFLPzYWJghNH7MjOd76qhr8m6vOfWpmffevefec987juu6AZ8RQhhBpJHJuT+CfsiEDo6wGjYeKMKn8b8Um/jCG2qQ0skjyOIWB9hFNyaN8bWSwGEHM5q9EVc6mUQ9YpjDHQbwoQkjuspDDKNEF9hjJDjFcoAEx653XEoJMYoVxNGBGPZRhzbL+HTYWLEtpO6V6EQ5kijTc7HFiwyssDwgyXsxhW8tkZSxAAksoj3n7P4G20hatviKE3RpqXKN4V5K4TE+IQ89WBI8ao0DFkP49krw+057xbyWxBY72LIdXsbjnlzf8/kRbtgSeO1APqonnwlu8tlBIYp9JojmkyCiX7Kf6MsngcSsvvO2aMZEPmcgEcea7ua/aNKGaC2RY0lwgTNsYwwNOlkrprGOJe2q/84vvegabdrrQyqomrSTyirHtbPKc+84x4L2qBazORi/s9KuC7QfBY3JC1UVBlGt16PallPap+Tas+7wWc8za1Ql8yPAAAzkXGo1lmDtAAAAAElFTkSuQmCC)}.photobooth .submenu{background:rgba( 0,0,0,.6 );position:absolute;width:100px;opacity:0;height:20px;padding:5px 10px;color:#fff;top:4px;left:-124px;border-radius:5px;-webkit-transition:opacity 500ms ease;-moz-transition:opacity 500ms ease;-o-transition:opacity 500ms ease;-msie-transition:opacity 500ms ease;transition:opacity 500ms ease}.photobooth li:hover .submenu{opacity:1}.photobooth .submenu .tip{width:4px;height:8px;position:absolute;right:-4px;top:50%;margin-top:-2px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAICAYAAADeM14FAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAADVJREFUeNpiYGBgmAnEDP///wdjJgYImMnIyAhmwATggowwLTCArAKrQDqyQDrcMGQlAAEGAAGOCdflbyWyAAAAAElFTkSuQmCC)}.photobooth .submenu .slider{width:100px;height:20px;position:relative}.photobooth .submenu .slider .track{height:2px;width:100px;position:absolute;top:9px;background:rgba(255,255,255,.6)}.photobooth .submenu .slider .handle{height:14px;width:2px;position:absolute;top:3;background:#fff;z-index:2}.photobooth .submenu .slider .handle div{position:absolute;z-index:3;width:20px;top:-3px;height:20px;cursor:w-resize;left:-9px}.resizehandle{position:absolute;z-index:1;width:100px;height:100px;left:30px;top:30px;cursor:move;outline:1500px solid rgba( 0,0,0,.35 );box-shadow:2px 2px 10px rgba(0,0,0,.5),0 0 3px #000;opacity:0;transition:opacity 500ms ease;-moz-transition:opacity 500ms ease;-o-transition:opacity 500ms ease;-webkit-transition:opacity 500ms ease}noindex:-o-prefocus,.resizehandle{outline:0!important}@-moz-document url-prefix(){.resizehandle{ box-shadow:none!important}}.resizehandle .handle{width:100%;height:100%;border:2px dashed #0da4d3;margin:-2px 0 0 -2px;z-index:3;position:relative}.resizehandle .handle div{width:18px;height:18px;position:absolute;right:-2px;bottom:-2px;z-index:4;cursor:se-resize;background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAHdJREFUeNpi/P//PwO5gIlcjXxLr/xnIlujsg7pNsM0AgEjE7kaSfIzusZ/d4n0M1aNxPgZWeMHC4RGIJuREV8847IRpBGvnwlpxBnPRGkEyYOcjYx5l1z+z3/8Pwij8NHlQWwUPxNrI4afSdUI9zNZGoF8gAADAOGvmx/e+CgVAAAAAElFTkSuQmCC);background-position:top left;background-repeat:no-repeat}";
    document.head.appendChild(s);
},false);
/**
 * JS
 */
Photobooth=function(e){
    e.length&&(e=e[0]);
    var t=navigator.getUserMedia||navigator.webkitGetUserMedia||navigator.mozGetUserMedia||navigator.oGetUserMedia||navigator.msieGetUserMedia||!1;
    this.onImage=function(){},this.pause=function(){
        l===!1&&(l=!0,c&&c.stop&&c.stop())
        },this.resume=function(){
        l===!0&&(l=!1,O())
        },this.destroy=function(){
        this.pause(),e.removeChild(y)
        },this.forceHSB=!1,this.isSupported=!!t,this.resize=function(e,t){
        if(e<200||t<200)throw"Error: Not enough space for Photobooth. Min height / width is 200 px";
        p=e,d=t,N.setMax(p,d),y.style.width=e+"px",y.style.height=t+"px",b.width=e,b.height=t,E.width=e,E.height=t,x.width=e,x.height=t
        };
        
    var n=function(e){
        e.stopPropagation(),e.cancelBubble=!0
        },r=function(e){
        this.startX=0,this.startY=0,e.addEventListener("mousedown",this,!1)
        };
        
    r.prototype.onStart=function(e,t){},r.prototype.onMove=function(e,t){},r.prototype.onStop=function(e,t){},r.prototype.handleEvent=function(e){
        this["fon"+e.type](e)
        },r.prototype.fonmousedown=function(e){
        e.preventDefault(),this.startX=e.clientX,this.startY=e.clientY,this.onStart(this.startX,this.startY),document.addEventListener("mousemove",this,!1),document.addEventListener("mouseup",this,!1)
        },r.prototype.fonmousemove=function(e){
        this.onMove(e.clientX-this.startX,e.clientY-this.startY)
        },r.prototype.fonmouseup=function(e){
        this.onStop(e.clientX-this.startX,e.clientY-this.startY),document.removeEventListener("mousemove",this),document.removeEventListener("mouseup",this)
        };
        
    var i=function(e,t){
        e.innerHTML='<div class="submenu"><div class="tip"></div><div class="slider"><div class="track"></div><div class="handle" style="left:50px"><div></div></div></div></div>';
        var i=50,s=50,o=e.getElementsByClassName("handle")[0],u=e.getElementsByClassName("slider")[0],a=new r(o);
        a.onMove=function(e){
            f(i+e)
            },a.onStop=function(e){
            i=s
            };
            
        var f=function(e){
            e>0&&e<100&&(s=e,o.style.left=e+"px",t((e-50)/100))
            },l=function(e){
            f(e.layerX),i=s
            };
            
        u.addEventListener("click",l,!1),o.addEventListener("click",n,!1)
        },s=function(e,t,i){
        this.setMax=function(e,n){
            t=e,i=n
            },this.getData=function(){
            return{
                x:s,
                y:o,
                width:u,
                height:a
            }
        },this.isActive=function(){
        return p
        },this.toggle=function(){
        p===!1?(d.style.opacity=1,p=!0):(d.style.opacity=0,p=!1)
        };
        
    var s=30,o=30,u=100,a=100,f=30,l=30,c=100,h=100,p=!1,d=document.createElement("div");
    d.className="resizehandle",d.innerHTML='<div class="handle"><div></div></div>',e.appendChild(d);
    var v=d.getElementsByTagName("div")[0],m=new r(v);
    m.onMove=function(e,n){
        s+e+u<t&&s+e>0&&(f=s+e,d.style.left=f+"px"),o+n+a<i&&o+n>0&&(l=o+n,d.style.top=l+"px")
        },m.onStop=function(){
        s=f,o=l
        };
        
    var g=d.getElementsByTagName("div")[1];
    g.addEventListener("mousedown",n,!1);
    var y=new r(g);
    y.onMove=function(e,n){
        s+e+u<t&&u+e>18&&(c=u+e,d.style.width=c+"px"),o+n+a<i&&a+n>18&&(h=a+n,d.style.height=h+"px")
        },y.onStop=function(){
        u=c,a=h
        }
    },o=0,u=0,a=0,f=!1,l=!1,c=null,h=this,p=e.offsetWidth,d=e.offsetHeight,v=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(e){
    window.setTimeout(e,1e3/60)
    },m=function(e){
    return y.getElementsByClassName(e)[0]
    },g=function(e){
    return document.createElement(e)
    },y=g("div");
y.className="photobooth",y.innerHTML='<div class="blind"></div><canvas></canvas><div class="warning notSupported">Sorry, Photobooth.js is not supported by your browser</div><div class="warning noWebcam">Please give Photobooth permission to use your Webcam. <span>Try again</span></div><ul><li title="hue"class="hue"></li><li title="saturation"class="saturation"></li><li title="brightness"class="brightness"></li><li title="crop"class="crop"></li><li title="take picture"class="trigger"></li></ul>';
var b=g("canvas"),w=b.getContext("2d"),E=y.getElementsByTagName("canvas")[0],S=E.getContext("2d"),x=g("video");
x.autoplay=!0;
var T=m("noWebcam");
T.getElementsByTagName("span")[0].onclick=function(){
    O()
    },new i(m("hue"),function(e){
    o=e
    }),new i(m("saturation"),function(e){
    u=e
    }),new i(m("brightness"),function(e){
    a=e
    });
var N=new s(y,p,d),C=m("crop");
C.onclick=function(){
    N.toggle(),C.className==="crop"?C.className="crop selected":C.className="crop"
    };
    
var k=m("blind");
m("trigger").onclick=function(){
    k.className="blind",k.style.opacity=1,setTimeout(function(){
        k.className="blind anim",k.style.opacity=0
        },50);
    var e={};
    
    N.isActive()?e=N.getData():f?e={
        x:(p-x.videoWidth)/2,
        y:(d-x.videoHeight)/2,
        width:x.videoWidth,
        height:x.videoHeight
        }:e={
        x:0,
        y:0,
        width:p,
        height:d
    };
    
    var t=g("canvas");
    t.width=e.width,t.height=e.height;
    if(f)t.getContext("2d").drawImage(x,Math.max(0,e.x-(p-x.videoWidth)/2),Math.max(e.y-(d-x.videoHeight)/2),e.width,e.height,0,0,e.width,e.height);
    else{
        var n=S.getImageData(e.x,e.y,e.width,e.height);
        t.getContext("2d").putImageData(n,0,0)
        }
        h.onImage(t.toDataURL())
    };
    
var L=function(e){
    c=e;
    try{
        x.src=(window.URL||window.webkitURL).createObjectURL(c),v(P)
        }catch(t){
        x.mozSrcObject=c,h.forceHSB===!1?(f=!0,y.appendChild(x),y.getElementsByTagName("ul")[0].className="noHSB"):x.addEventListener("canplay",function(){
            v(P)
            },!1),x.play()
        }
    },A=function(e){
    T.style.display="block"
    },O=function(){
    T.style.display="none",t.call(navigator,{
        video:!0
        },L,A)
    },M=function(e,t,n){
    return n<0&&(n+=1),n>1&&(n-=1),n<1/6?e+(t-e)*6*n:n<.5?t:n<2/3?e+(t-e)*(2/3-n)*6:e
    },_=function(e){
    return e>1?e-1:e<0?1+e:e
    },D=function(e){
    return e>1?1:e<0?0:e
    },P=function(){
    w.drawImage(x,0,0,p,d);
    var e=w.getImageData(0,0,p,d),t=e.data;
    for(var n=0;n<t.length;n+=4){
        var r=t[n]/255,i=t[n+1]/255,s=t[n+2]/255,f=Math.max(r,i,s),c=Math.min(r,i,s),h,m,g=(f+c)/2;
        if(f==c)h=m=0;
        else{
            var y=f-c;
            m=g>.5?y/(2-f-c):y/(f+c),f===r&&(h=((i-s)/y+(i<s?6:0))/6),f===i&&(h=((s-r)/y+2)/6),f===s&&(h=((r-i)/y+4)/6)
            }
            h=_(h+o),m=D(m+u),g=D(g+a);
        if(m===0)r=i=s=g;
        else{
            var b=g<.5?g*(1+m):g+m-g*m,E=2*g-b;
            r=M(E,b,h+1/3),i=M(E,b,h),s=M(E,b,h-1/3)
            }
            t[n]=r*255,t[n+1]=i*255,t[n+2]=s*255
        }
        S.putImageData(e,0,0),l===!1&&v(P)
    };
    
this.resize(p,d),e.appendChild(y),t?v(O):m("notSupported").style.display="block"
}
