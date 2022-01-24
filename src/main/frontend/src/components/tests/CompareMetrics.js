import {Container} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import axios from "axios";

const ARTICLE_URL = "/metrics/api/article";

export default function CompareMetrics(){
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const doi = params.get("doi");
    const title = params.get("title");
    const author = params.get("author");

    const [articleByTitle, setArticleByTitle] = useState(null);
    const [articleByDoi, setArticleByDoi] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadArticle = async () => {
            const responseDoi = await axios.get(ARTICLE_URL + "?doi=" + doi);
            setArticleByDoi(responseDoi.data);
            const responseTitle = await axios.get(ARTICLE_URL + "?title=" + title + "&author=" + author);
            setArticleByTitle(responseTitle.data);
            setLoading(false);
        }
        loadArticle()
    }, [doi, title, author]);

    console.log(doi);
    console.log(title);

    const renderTable = !loading && articleByDoi !== null && articleByTitle !== null ?
        <table className='table table-striped' aria-labelledby="tabelLabel">
            <thead>
            <tr>
                <th> </th>
                <th>Article Added By DOI</th>
                <th>Article Added By Title</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>Mendeley</th>
                <td>
                    {articleByDoi.mendeley.readersCount}
                </td>
                <td>
                    {articleByTitle.mendeley.readersCount}
                </td>
            </tr>
            <tr>
                <th>Crossref</th>
                <td>
                    {articleByDoi.crossref.referencedByCount}
                </td>
                <td>
                    {articleByTitle.crossref.referencedByCount}
                </td>
            </tr>
            <tr>
                <th>Scopus</th>
                <td>
                    {articleByDoi.scopus.citationsCount}
                </td>
                <td>
                    {articleByTitle.scopus.citationsCount}
                </td>
            </tr>
            <tr>
                <th>Wikipedia</th>
                <td>
                    {articleByDoi.wikipedia.totalHits}
                </td>
                <td>
                    {articleByTitle.wikipedia.totalHits}
                </td>
            </tr>
            <tr>
                <th>Reddit</th>
                <td>
                    {articleByDoi.reddit.totalResults}
                </td>
                <td>
                    {articleByTitle.reddit.totalResults}
                </td>
            </tr>
            <tr>
                <th>Twitter</th>
                <td>
                    {articleByDoi.twitter.resultCount}
                </td>
                <td>
                    {articleByTitle.twitter.resultCount}
                </td>
            </tr>
            <tr>
                <th>Youtube</th>
                <td>
                    {articleByDoi.youtube.totalResults}
                </td>
                <td>
                    {articleByTitle.youtube.totalResults}
                </td>
            </tr>
            <tr>
                <th>Facebook reaction sum</th>
                <td>
                    {articleByDoi.facebook.reactionCount + articleByDoi.facebook.shareCount + articleByDoi.facebook.commentCount}
                </td>
                <td>
                    -
                </td>
            </tr>
            <tr>
                <th>StackExchange</th>
                <td>
                    {articleByDoi.stackExchange.totalCount}
                </td>
                <td>
                    {articleByTitle.stackExchange.totalCount}
                </td>
            </tr>
            <tr>
                <th>News</th>
                <td>
                    {articleByDoi.news.totalResults}
                </td>
                <td>
                    -
                </td>
            </tr>
            <tr>
                <th>EventData Twitter</th>
                <td>
                    {articleByDoi.eventDataTwitter.totalResults}
                </td>
                <td>
                    -
                </td>
            </tr>
            </tbody>
        </table>
        : "Error loading";



    return(
        <Container>
            <h4>
                Compare metrics collected by title and by DOI
            </h4>
            {renderTable}
        </Container>
    );
}