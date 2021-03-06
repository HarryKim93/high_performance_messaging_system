import React from 'react';
import './App.css';
import SockJsClient from "react-stomp";
import UsernameGenerator from "username-generator";
import Fetch from "json-fetch";
import { TalkBox } from "react-talk";
import randomstring from"randomstring";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.randomUserName = UsernameGenerator.generateUsername("-");
        this.randomUserId = randomstring.generate();
        this.state = {
            clientConnected : false,
            messages : []
        };

    }

    onMessageReceive = (msg, topic) => {
        this.setState(prevState => ({
            messages: [...prevState.messages, msg]
        }));
    }

    sendMessage = (msg, selfMsg) => {
        try {
            var send_message = {
                "user" : selfMsg.author,
                "message" : selfMsg.message
            }
            this.clientRef.sendMessage("/app/message", JSON.stringify(send_message));
            //clientRef -> SockJS를 내부에 들고있는 Client
            return true;
        } catch(e) {
            return false;
        }
    }

    componentWillMount() {
        console.log("call history");
        Fetch("/history", {
            method: "GET"
        }).then((response) => {
            this.setState({ messages: response.body });
        });
    }

    render() {
        const wsSourceUrl = "http://localhost:8080/chatting";
        return (
            <div>
                <TalkBox topic="Test for /topic/public"
                         currentUserId={ this.randomUserId }
                         currentUser={ this.randomUserName }
                         messages={ this.state.messages }
                         onSendMessage={ this.sendMessage }
                         connected={ this.state.clientConnected }/>

                <SockJsClient url={ wsSourceUrl }
                              topics={["/topic/public"]}
                              onMessage={ this.onMessageReceive }
                              ref={ (client) => { this.clientRef = client }}
                              onConnect={ () => {this.setState({ clientConnected: true }) } }
                              onDisconnect={ () => { this.setState({ clientConnected: false }) } }
                              debug={ false } style={[{width:'100%', height:'100%'}]}/>
            </div>
        );
    }
}

export default App;