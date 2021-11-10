import React, {useState, useEffect} from 'react'
import axios from 'axios';
import { Tweet } from 'react-twitter-widgets'
import {Row, Col, Form, Container} from 'react-bootstrap';

export default function Article(){
    const ARTICLES_URL = 'http://localhost:8080/articles';
    const [article, setArticle] = useState(null);
    const [allArticles, setAllArticles] = useState([]);
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');

    function getItemById(){
        axios.get(ARTICLES_URL + '/' + doiGet)
            .then((response) => {
                setArticle(response.data)
            })
            .catch(error => console.error('Error: ' + error));
    }

    function addItem(){
        console.log('You want to add article: ' + doiAdd);
        axios.post(ARTICLES_URL, {id: doiAdd})
            .then((response) => {
                console.log(response)
                alert('Article successfully added')
            })
            .catch(error => console.error(error))
    }

    function getAllItems(){
        axios.get(ARTICLES_URL)
            .then((response) => {
                setAllArticles(response.data)
            })
            .catch(error => console.error('Error: ' + error));
    }

    useEffect(() => {
        console.log(article)
    }, [article])



    function handleGetSubmit(e) {
        getItemById();
        e.preventDefault();
    }

    function handleGetAllSubmit(e) {
        getAllItems();
        e.preventDefault();
    }

    function handleAddSubmit(e) {
        addItem();
        e.preventDefault();
    }

    function handleDoiGetChange(e) {
        setDoiGet(e.target.value);
        e.preventDefault();
    }
    function handleDoiAddChange(e) {
        setDoiAdd(e.target.value);
        e.preventDefault();
    }

    return (
        <Container>
        <Row>
            <Col>
                <Form onSubmit={handleGetSubmit}>
                    <label>Get article by DOI</label>
                    <br/>
                    <input type="text" value={doiGet} onChange={handleDoiGetChange}/>
                    <br/>
                    <input type="submit" value="Confirm"/>
                </Form>
                {article && article.youtube.items.map(search =>{
                    return(
                        <div key={search.videoId}>
                            <p>{search.title}</p>
                            <p>{search.description}</p>
                            <p>{search.publishedAt}</p>
                            <img src={search.thumbnailUrl} alt=""/>
                        </div>
                    )
                })}
                {!article &&
                    <div>
                        This article is not yet in the base
                    </div>
                }
                {/*{article && article.twitter.results.map(search =>{*/}
                {/*    return(*/}
                {/*        <Tweet tweetId={search.id} />*/}
                {/*    )*/}
                {/*})}*/}
            </Col>
            <Col>
                <Form onSubmit={handleAddSubmit}>
                    <label>Add article</label>
                    <br/>
                    <input type="text" value={doiAdd} onChange={handleDoiAddChange}/>
                    <br/>
                    <input type="submit" value="Confirm"/>
                </Form>
            </Col>
            <Col>
                <Form onSubmit={handleGetAllSubmit}>
                    <input type="submit" value="Get all articles" />
                </Form>
                {allArticles && allArticles.map(article =>
                    article && article.youtube.items.map(search =>

                        <div key={search.videoId}>
                            <p>{search.title}</p>
                            <p>{search.description}</p>
                            <p>{search.publishedAt}</p>
                            <img src={search.thumbnailUrl} alt=""/>
                        </div>
                    )
                )}
            </Col>
        </Row>
        </Container>
    );
}