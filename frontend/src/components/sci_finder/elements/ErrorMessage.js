import React from "react";

class ErrorMessage extends React.Component {
    onCloseClick = () => {
        if (this.props.onCloseClick) {
            this.props.onCloseClick();
        }
    }

    render() {
        return (
            <div className="ui negative message">
                <i onClick={() => this.onCloseClick()} className="close icon"></i>
                <div className="header">
                    {this.props.title}
                </div>
                <p>
                    {this.props.message}
                </p>
            </div>
        );
    }
}

export default ErrorMessage;