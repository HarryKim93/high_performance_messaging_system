import React, {Component} from "react";
import { TalkBox } from "react-talk";
import SockJsClient from "react-stomp";
import UsernameGenerator from "username-generator";
import Fetch from "json-fetch";
import randomstring from "randomstring";

class chatRoom extends Component{
    constructor(props) {
        super(props);
        this.randomUserName = UsernameGenerator.generateUsername("-");
        this.randomUserId = randomstring.generate();
        this.sendURL = "/message";
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
                <TalkBox topic="/topic/public" currentUserId={this.randomUserId}
                    currentUser={this.randomUserName} messages={this.state.messages}
                    onSendMessage={this.sendMessage} connected={this.state.clientConnected}>
                </TalkBox>

                <SockJsClient url={wsSourceUrl} topics={["/topic/public"]}>
                    onMessge={this.onMessageReceive} ref={ (client) => {this.clientRef = client}}
                    onConnect={ () => {this.setState({ clientConnected: true }) } }
                    onDisconnect={ () => { this.setState({ clientConnected: false }) } }
                    debug={ false } style={[{width:'100%', height:'100%'}]}>
                </SockJsClient>

            </div>
        );
    }
}

export default chatRoom;
