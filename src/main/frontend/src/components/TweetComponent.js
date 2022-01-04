import {useEffect} from "react";
import { Tweet } from "react-twitter-widgets"

export default function TweetComponent({tweet}){
    // useEffect(() =>{
    //     window.twttr.widgets.load();
    // }, []);

    return(
        // <blockquote className="twitter-tweet" data-conversation="none" data-cards="hidden">
        //     <a href={"https://twitter.com/" + tweet.author + "/status/" + tweet.tweet } />
        // </blockquote>
        <Tweet tweetId={tweet} options={{conversation: "none", dnt: "true"}}/>
    );
}