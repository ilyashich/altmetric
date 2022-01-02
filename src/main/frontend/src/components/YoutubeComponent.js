import Moment from 'moment'
import './YoutubeComponent.css'
import {useState} from "react";
import Pagination from "./Pagination";

export default function YoutubeComponent( {youtube} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [videosPerPage] = useState(10);

    const indexOfLastVideo = currentPage * videosPerPage;
    const indexOfFirstVideo = indexOfLastVideo - videosPerPage;
    const currentVideos = youtube.items.slice(indexOfFirstVideo, indexOfLastVideo);
    const firstColumn = currentVideos.slice(0, 5);
    const secondColumn = currentVideos.slice(5, currentVideos.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="row">
            <div className="col">
                {firstColumn.map(item =>
                    <article key={item.videoId} className="postVideos">
                        <a target="_blank" rel="noreferrer" className="block_link" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                            <img alt={item.channelTitle} className="avatar" src={item.thumbnailUrl} />
                            <div className="contentWith_image">
                                <h3 id="header3">{item.title}</h3>
                                <h4 id="header4">User <strong id="laysStrong">{item.channelTitle}</strong> on YouTube,
                                    <time id="datetime" dateTime={item.publishedAt}>{Moment(item.publishedAt).format("DD MMMM YYYY")}</time></h4>
                                <p id="paragraph" className="summary">{item.description}</p>
                            </div>
                        </a>
                    </article>
                )}
            </div>
            <div className="col">
                {secondColumn.map(item =>
                    <article key={item.videoId} className="postVideos">
                        <a target="_blank" rel="noreferrer" className="block_link" href={"https://www.youtube.com/watch?v=" + item.videoId}>
                            <img alt={item.channelTitle} className="avatar" src={item.thumbnailUrl} />
                            <div className="contentWith_image">
                                <h3 id="header3">{item.title}</h3>
                                <h4 id="header4">User <strong id="laysStrong">{item.channelTitle}</strong> on YouTube,
                                    <time id="datetime" dateTime={item.publishedAt}>{Moment(item.publishedAt).format("DD MMMM YYYY")}</time></h4>
                                <p id="paragraph" className="summary">{item.description}</p>
                            </div>
                        </a>
                    </article>
                )}
            </div>
            <Pagination itemsPerPage={videosPerPage} totalItems={youtube.items.length} paginate={paginate} />
        </div>
    );
}



