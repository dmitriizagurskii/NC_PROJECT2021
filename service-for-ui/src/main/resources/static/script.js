var stompClient;
var id;
var myCodeMirror;
var roomId = "15c67807-3be7-4512-b361-b6923485bf44";


$(function() {

    // Creating div container for editor
    const editorContainer = document.createElement("div");
    editorContainer.setAttribute("id", "editor");
    document.body.insertBefore(editorContainer, null);

    // Creating codeMirror instance
    myCodeMirror = CodeMirror(editorContainer, {
        value: "",
        mode:  "java",
        lineNumbers: true
    });

    // listening to buttons events
    $('#button-connect').click(function() {connect()});
    $('#button-disconnect').click(function() {disconnect()});


    // listening to change event and send change to server if it appears
    myCodeMirror.on("change", function (cm, change) {
        console.log("Change: \n"
            + "from - line:" + change.from.line + ", ch: " + change.from.ch
            + "\nto- line:" + change.to.line + ", ch: " + change.to.ch
            + "\ntext: " + change.text + "\nremoved: " + change.removed
            + "\norigin: " + change.origin);
        sendChange(change);
    });



});


function connect() {
    console.log('CONNECTING');
    const socket = new SockJS('http://localhost:8080/ws');
    if (typeof stompClient !== 'undefined') stompClient.disconnect();
    myCodeMirror.setValue("");

    stompClient = Stomp.over(socket);

    id = $('#input-name').val();

    fetchAndSetText(roomId);


    // set connection through websocket
    stompClient.connect({}, function(frame) {
        stompClient.subscribe(`/interview-room/${roomId}`, function (change) {
            updateText(JSON.parse(change.body));
        });

    });

}


// updating text
function updateText(change) {
    if (change.uid !== id) {
        var doc = myCodeMirror.getDoc();
        var changeInfo = change.codeChangeInfo;

        if (checkForNewLine(changeInfo)) {
            doc.replaceRange("\n", changeInfo.from, changeInfo.to, '@ignore');
        }
        else {
            console.log(changeInfo);
            doc.replaceRange(changeInfo.text, changeInfo.from, changeInfo.to, changeInfo.origin);
        }
    }
}

// Checks if received change requires insertion of new line
function checkForNewLine(changeInfo) {
    return changeInfo.text == ""
        && changeInfo.from.line == changeInfo.to.line
        && changeInfo.from.ch == changeInfo.to.ch;

}


// send change to server through websocket
function sendChange(change) {
    stompClient.send(
        `/destinations/${roomId}`,
        {},
        JSON.stringify({'UID': id, 'time': Date.now().toString(),
            'codeChangeInfo': {
                'from': {'line': change.from.line, 'ch': change.from.ch},
                'to': {'line': change.to.line, 'ch': change.to.ch},
                'text': change.text[0],
                'origin': '@ignore'
            }})
    );
}


// send GET request to get text by room id
function fetchAndSetText(id) {
    const url = `http://localhost:8080/interview-room/${id}/code`;
    console.log(url);
    $.get(url, function (data) {
        myCodeMirror.getDoc().setValue(data);
    });
}




// disconnect websocket
function disconnect() {
    if (stompClient != null) {
        myCodeMirror.setValue("");
        stompClient.disconnect();
    }
}

