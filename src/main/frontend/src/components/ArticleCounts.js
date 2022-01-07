
export default function ArticleCounts( { article } ){
    const mendeleyCount = article && article.mendeley.reader_count > 0 ?
        <li> Readers
            <ul>
                <li>
                    <a target="_blank" rel="noreferrer" href={article.mendeley.link}>
                        Mendeley:
                    </a>
                    {article.mendeley.reader_count}
                </li>
            </ul>
        </li>
        : null;
    const scopusCount = article && article.scopus.citationsCount > 0 ?
        <li>
            <a target="_blank" rel="noreferrer" href={article.scopus.link}>
                Scopus:
            </a>
            {article.scopus.citationsCount}
        </li>
        : null;
    const crossrefCount = article && article.crossref.referencedByCount > 0 ?
        <li>
            Crossref: {article.crossref.referencedByCount}
        </li>
        : null;
    const wikipediaCount = article && article.wikipedia.totalHits > 0 ?
        <li>
            Wikipedia: {article.wikipedia.totalHits}
        </li>
        : null;
    const redditCount = article && article.reddit.articles.length > 0 ?
        <li>
            Reddit: {article.reddit.articles.length}
        </li>
        : null;
    const twitterCount = article.twitter.resultCount > 0 || article.eventDataTwitter.totalResults > 0 ?
        <li>
            Twitter: {article.twitter.resultCount + article.eventDataTwitter.totalResults}
        </li>
        : null;
    const youtubeCount = article.youtube.totalResults > 0 ?
        <li>
            Youtube: {article.youtube.totalResults}
        </li>
        : null;
    const facebookCount = article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount > 0 ?
        <li>
            Facebook:
            <ul>
                <li>
                    Likes: {article.facebook.reactionCount}
                </li>
                <li>
                    Shares: {article.facebook.shareCount}
                </li>
                <li>
                    Comments: {article.facebook.commentCount}
                </li>
            </ul>
        </li>
        : null;
    const stackExchangeCount = article.stackExchange.items.length > 0 ?
        <li>
            Q&A: {article.stackExchange.items.length}
        </li>
        : null;

    const newsCount = article.news.totalResults > 0 ?
        <li>
            News: {article.news.totalResults}
        </li>
        : null;


   const citations = scopusCount || crossrefCount ?
       <li>Citations
           <ul>
               {scopusCount}
               {crossrefCount}
           </ul>
       </li>
       : null;

   const mentions = wikipediaCount || redditCount || stackExchangeCount || newsCount || youtubeCount ?
       <li>Mentions
           <ul>
               {wikipediaCount}
               {redditCount}
               {stackExchangeCount}
               {newsCount}
               {youtubeCount}
           </ul>
       </li>
       : null;


    const socialMedia = twitterCount || facebookCount ?
        <li>Social Media
            <ul>
                {twitterCount}
                {facebookCount}
            </ul>
        </li>
        : null;


    return(
        <ul className="text-start">
            <h5>
                <strong>Metrics</strong>
            </h5>
            {mendeleyCount}
            {citations}
            {mentions}
            {socialMedia}
        </ul>
    );
}