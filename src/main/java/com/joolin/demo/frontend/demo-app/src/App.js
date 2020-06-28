import React from "react";
import './App.css';

import chatRoom from "./component/chatRoom";

class App extends React.Component{

    render(){
        const wsSourceUrl = "http://localhost:8080/chatting";
        return(
            <div>
                <chatRoom>
                </chatRoom>
            </div>
        )
    }
}

export default App;