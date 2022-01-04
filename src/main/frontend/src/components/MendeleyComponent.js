import { Row, Col} from "react-bootstrap";

export default function MendeleyComponent( {mendeley} ){

    let academicStatus = [];
    let academicStatusSum = 0;
    let academicStatusOther = 0;
    let academicStatusUnknown = 0;

    Object.keys(mendeley.reader_count_by_academic_status).map((entry) => {
        if(parseFloat(mendeley.reader_count_by_academic_status[entry]) / parseFloat(mendeley.reader_count) > 0.03 && entry !== "Other"){
            academicStatus.push( [entry, parseInt(mendeley.reader_count_by_academic_status[entry]) ]);
        }
        else{
            academicStatusOther += parseInt(mendeley.reader_count_by_academic_status[entry]);
        }
        academicStatusSum += parseInt(mendeley.reader_count_by_academic_status[entry]);
    });

    if(academicStatusSum < parseInt(mendeley.reader_count)){
        academicStatusUnknown = parseInt(mendeley.reader_count) - academicStatusSum;
    }

    academicStatus.sort((a, b) =>  b[1] - a[1]);


    let subjectArea = [];
    let subjectAreaSum = 0;
    let subjectAreaOther = 0;
    let subjectAreaUnknown = 0;

    Object.keys(mendeley.reader_count_by_subject_area).map((entry) => {
        if(parseFloat(mendeley.reader_count_by_subject_area[entry]) / parseFloat(mendeley.reader_count) > 0.03 && entry !== "Other"){
            subjectArea.push( [entry, parseInt(mendeley.reader_count_by_subject_area[entry]) ]);
        }
        else{
            subjectAreaOther += parseInt(mendeley.reader_count_by_subject_area[entry]);
        }
        subjectAreaSum += parseInt(mendeley.reader_count_by_subject_area[entry]);
    });

    if(subjectAreaSum < parseInt(mendeley.reader_count)){
        subjectAreaUnknown = parseInt(mendeley.reader_count) - subjectAreaSum;
    }

    subjectArea.sort((a, b) =>  b[1] - a[1]);

    return(
        <Row>
            <Col>
                <table className='table table-striped' aria-labelledby="tabelLabel">
                    <thead>
                    <tr>
                        <th>Readers By Academic status</th>
                        <th>Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    {academicStatus.map((entry, i) =>
                        <tr key={i}>
                            <td>{entry[0]}</td>
                            <td>{entry[1]}</td>
                        </tr>
                    )}
                    {academicStatusOther > 0 &&
                        <tr>
                            <td>
                                Other
                            </td>
                            <td>
                                {academicStatusOther}
                            </td>
                        </tr>
                    }
                    {academicStatusUnknown > 0 &&
                        <tr>
                            <td>
                                Unknown
                            </td>
                            <td>
                                {academicStatusUnknown}
                            </td>
                        </tr>
                    }
                    </tbody>
                </table>
            </Col>
            <Col>
                <table className='table table-striped' aria-labelledby="tabelLabel">
                    <thead>
                    <tr>
                        <th>Readers By Subject area</th>
                        <th>Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    {subjectArea.map((entry, i) =>
                        <tr key={i}>
                            <td>{entry[0]}</td>
                            <td>{entry[1]}</td>
                        </tr>
                    )}
                    {subjectAreaOther > 0 &&
                        <tr>
                            <td>
                                Other
                            </td>
                            <td>
                                {subjectAreaOther}
                            </td>
                        </tr>
                    }
                    {subjectAreaUnknown > 0 &&
                        <tr>
                            <td>
                                Unknown
                            </td>
                            <td>
                                {subjectAreaUnknown}
                            </td>
                        </tr>
                    }
                    </tbody>
                </table>
            </Col>
        </Row>

    );
}