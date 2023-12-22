import React from "react";
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <div className="ui secondary pointing menu">
            <Link to="/" className="item">
                SciTinder
            </Link>
            <div className="right menu">
                <Link to="/about-us" className="item">
                    About us
                </Link>
            </div>
        </div>
    );
};

export default Header;