import Moment from 'moment'
import "./YoutubeComponent.css"
import {useState} from "react";
import Pagination from "../Pagination";
import {Button, Card, Col} from "react-bootstrap";

export default function YoutubeComponent( {youtube} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [videosPerPage] = useState(12);

    const indexOfLastVideo = currentPage * videosPerPage;
    const indexOfFirstVideo = indexOfLastVideo - videosPerPage;
    const currentVideos = youtube.items.slice(indexOfFirstVideo, indexOfLastVideo);
    const firstColumn = currentVideos.slice(0, 4);
    const secondColumn = currentVideos.slice(4, 8);
    const thirdColumn = currentVideos.slice(8, currentVideos.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const toPlainText = (html) => {
        let tempDivElement = document.createElement("div");
        tempDivElement.innerHTML = html;
        return tempDivElement.textContent || tempDivElement.innerText || "";
    }

    return (
        <div className="row" style={{ marginTop: '20px'}}>

            {firstColumn.map((item, i) =>
                <Col key={i}>
                    <Card className="youtube-card" >
                        <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                            <Card.Img className="youtube-card-image" variant="top" src={item.thumbnailUrl} />
                        </a>
                        <Card.Body>
                            <Card.Title>
                                <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                    {toPlainText(item.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="youtube-card-subtitle">
                                {item.channelTitle}, {Moment(item.publishedAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text>
                                {item.description}
                            </Card.Text>

                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                Go to video
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
            {secondColumn.map((item, i) =>
                <Col key={i}>
                    <Card className="youtube-card">
                        <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                            <Card.Img className="youtube-card-image" variant="top" src={item.thumbnailUrl} />
                        </a>
                        <Card.Body>
                            <Card.Title>
                                <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                    {toPlainText(item.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="youtube-card-subtitle">
                                {item.channelTitle}, {Moment(item.publishedAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text>
                                {item.description}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                Go to video
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
            {thirdColumn.map((item, i) =>
                <Col key={i}>
                    <Card className="youtube-card">
                        <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                            <Card.Img className="youtube-card-image" variant="top" src={item.thumbnailUrl} />
                        </a>
                        <Card.Body>
                            <Card.Title>
                                <a className="youtube-card-title" target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                    {toPlainText(item.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="youtube-card-subtitle">
                                {item.channelTitle}, {Moment(item.publishedAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text>
                                {item.description}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                                Go to video
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
            <Pagination itemsPerPage={videosPerPage} totalItems={youtube.items.length} paginate={paginate} />
        </div>
    );
}



