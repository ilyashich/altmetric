import {useEffect} from "react";

export default function Tweet({tweet}){
    useEffect(() =>{
        window.twttr = (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0],
                t = window.twttr || {};
            if (d.getElementById(id)) return t;
            js = d.createElement(s);
            js.id = id;
            js.src = "https://platform.twitter.com/widgets.js";
            fjs.parentNode.insertBefore(js, fjs);

            t._e = [];
            t.ready = function(f) {
                t._e.push(f);
            };

            return t;
        }(document, "script", "twitter-wjs"));
        window.twttr.widgets.load(document.getElementById('blockquote'));
    }, [])
    return(
        <blockquote className="twitter-tweet" data-conversation="none" data-cards="hidden">
            <a href={"https://twitter.com/" + tweet.authorId + "/status/" + tweet.tweetId } />
        </blockquote>
    )
}