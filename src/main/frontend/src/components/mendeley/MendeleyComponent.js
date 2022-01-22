import { Row, Col} from "react-bootstrap";

export default function MendeleyComponent( {mendeley} ){

    let academicStatus = [];
    let academicStatusSum = 0;
    let academicStatusOther = 0;
    let academicStatusUnknown = 0;

    Object.keys(mendeley.readerCountByAcademicStatus).forEach((entry) => {
        if(parseFloat(mendeley.readerCountByAcademicStatus[entry]) / parseFloat(mendeley.readersCount) > 0.03 && entry !== "Other"){
            academicStatus.push( [entry, parseInt(mendeley.readerCountByAcademicStatus[entry]) ]);
        }
        else{
            academicStatusOther += parseInt(mendeley.readerCountByAcademicStatus[entry]);
        }
        academicStatusSum += parseInt(mendeley.readerCountByAcademicStatus[entry]);
    });

    if(academicStatusSum < parseInt(mendeley.readersCount)){
        academicStatusUnknown = parseInt(mendeley.readersCount) - academicStatusSum;
    }

    academicStatus.sort((a, b) =>  b[1] - a[1]);


    let subjectArea = [];
    let subjectAreaSum = 0;
    let subjectAreaOther = 0;
    let subjectAreaUnknown = 0;

    Object.keys(mendeley.readerCountBySubjectArea).forEach((entry) => {
        if(parseFloat(mendeley.readerCountBySubjectArea[entry]) / parseFloat(mendeley.readersCount) > 0.03 && entry !== "Other"){
            subjectArea.push( [entry, parseInt(mendeley.readerCountBySubjectArea[entry]) ]);
        }
        else{
            subjectAreaOther += parseInt(mendeley.readerCountBySubjectArea[entry]);
        }
        subjectAreaSum += parseInt(mendeley.readerCountBySubjectArea[entry]);
    });

    if(subjectAreaSum < parseInt(mendeley.readersCount)){
        subjectAreaUnknown = parseInt(mendeley.readersCount) - subjectAreaSum;
    }

    subjectArea.sort((a, b) =>  b[1] - a[1]);

    return(
        <Row style={{ marginTop: '20px'}}>
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