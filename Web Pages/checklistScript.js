/* 
Accepts a JSON string and turns that into a checklist that consists of clubs and checkboxes

---notes---
    - Because we are still working on the database I have creates a false JSON string objects that becomes parsed and is used.
    - Later when we connect servers the parameters of this method will change so that it has "(var jsonText)" where it accepts a json string
        - also at that point we need to remove my placeholder JSON string
    - the json string MUST!!!! be written as such
        -"{"name":[list of clubs], "check"[list of booleans that tells me if the user has previously chosen to select this option or not]}
    
    **--NEED TO KNOWS--**
    - when writing the code in HTML, this will look for the ID "checklist" and write everything in there.
        -make sure that i have a <p> or a <div> with the ID of "checklist"
    - this implements the "style.css" so include it in the HTML file
*/
function makeCheckList(){
    //------------edit when server connection is up-------------//
    //placeholder JSON string and object    
    var jsonText = '{"name":["club","cal","one","2","3","4","5","6","7","8","9","0"], "check":[true, false, false, false, false, false, false, false, false, false, false, false]}';
    //---------------------------------------------------------//
    
    
    /*
    clears whatever is in the checklist element
        this is in here so that there will not be multiple checkboxes of the same organization appearing on the checklist.
        HOWEVER, if this only can be loaded once this line can be removed
    */
    document.getElementById("checklist").innerHTML="";

    var parsedString = JSON.parse(jsonText);
    var table = document.createElement("table");
    table.setAttribute("id", "checktable");
    table.style.width = '100%';
    var tBody = document.createElement("tbody");
    var tr;
    for(var i = 0; i< parsedString.name.length; i++){
        
        //initializes the "checkBoxObj" to become a checkbox + name
        var checkBoxObj = document.createElement("input");
        checkBoxObj.type = "checkbox";
        checkBoxObj.id = parsedString.name[i];
        checkBoxObj.className="css-checkbox";
        checkBoxObj.setAttribute("name", "organization");
        if(parsedString.check[i])
            checkBoxObj.setAttribute("checked", true);
        
        //creates a lable so the user can click to check and see what they are clicking on
        var label = document.createElement("label");
        label.setAttribute("for", parsedString.name[i]);
        label.setAttribute("name", "checkbox");
        label.className="css-label";
        label.innerHTML = parsedString.name[i];
        ////////////////////////////////////////////////////////
        if(i %3 == 0){
            tr = document.createElement("tr");
            tBody.append(tr);
        }
        var td = document.createElement("td");
        td.append(checkBoxObj);
        td.append(label);
        tr.append(td);
        if (i%3 != 0 && i+1 == parsedString.name.lengt){
            tBody.append(tr);
        }
        // adds the checkbox and label to the document
        //document.getElementById("checklist").append(checkBoxObj);
        //document.getElementById("checklist").append(label);
        //document.getElementById("checklist").innerHTML += "<br>";
    }
    table.append(tBody);
    document.getElementById("checklist").append(table);
}

/*
    scans though the HTML document and collects data to send to the database in JSON format
    
    --note--
        - Because theres no way to send data to the database yet this will use the "window.alert" function to show what would have been sent. 
        - when connected to the database make sure that "return JSON.stringify(sendMe);" is written at the end of this
            - also remove the "window.alert" when that time comes
        --- i still dont know how to send files to the server so this function only creates a JSON string from the HTML code
            
    **--NEED TO KNOW--**
        - this will send a JSON string to the server in the same format that was requested
            - "{"name":[list of clubs], "check"[list of booleans that tells me if the user has previously chosen to select this option or not]}
        - this is used when the "submit" button is hit and a JSON string needs to be generated to be sent to the server       
*/
function sendJSONStringToServer(){
    // creates a JSON object that holds two arrays: name(string), check(boolean)
    var sendMe = {"name":[] , "check":[]};    
    var orgArrayList= document.getElementsByName("organization");
    
    //itterates though the for loop inputting all values into the JSON object
    for (var i =0; i< orgArrayList.length; i++){
        sendMe.name.push(orgArrayList[i].getAttribute("id"));
        sendMe.check.push(orgArrayList[i].checked);
    }
    /*
        Outputs an alert to the windo of the contents of the JSON string
        
        -NOTE-
        make sure to replace this with "return JSON.stringify(sendMe)" when we are on the step where we need to talk to the servers
    */
    window.alert(JSON.stringify(sendMe));
}



/*
Combines all functions so that when submit button is pressed it does all the things it needs to do

--NOTE--
    -- the website needs to be changed to the landing page
        -- currently google.com is placed as a placeholder
*/
function submitButtonPress(){
    sendJSONStringToServer();
    location.href = 'https://www.google.com';
}


//

$(window).ready(function(){
$(".boton").wrapInner('<div class=botontext></div>');
    
    $(".botontext").clone().appendTo( $(".boton") );
    
    $(".boton").append('<span class="twist"></span><span class="twist"></span><span class="twist"></span><span class="twist"></span>');
    
    $(".twist").css("width", "25%").css("width", "+=3px");
});