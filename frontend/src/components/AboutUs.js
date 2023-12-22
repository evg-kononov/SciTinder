import React from "react";
import DuckImage from "../images/duck.png";

const AboutUs = () => {
    return (
        <div className="ui container">
            <a href="https://pstu.ru/manage/rectorat/" className="ui medium image">
                <img src={DuckImage}/>
            </a>
            <div className="ui hidden divider"/>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <i className="big olive dollar sign icon"></i>
            <div className="ui hidden divider"/>
            <i className="big olive money bill alternate icon"></i>
            <i className="big olive money bill alternate icon"></i>
            <i className="big olive money bill alternate icon"></i>
            <div className="ui hidden divider"/>
            <a className="ui huge label red">
                $5000.00 Vadim
            </a>
            <a className="ui huge label red">
                $5000.00 Evgeniy
            </a>
            <div className="ui hidden divider"/>
            <a className="ui huge label green">
                +79128878008 Sber
            </a>
            <a className="ui huge label yellow">
                +79048424647 Tinkoff
            </a>
        </div>
    );
};

export default AboutUs;