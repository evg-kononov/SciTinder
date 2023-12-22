import React from "react";

class Pages extends React.Component {

    onPrevious = () => {
        if (this.props.currentPage > 0) {
            this.props.onPageChange(this.props.currentPage - 1);
        }
    }

    onNext = () => {
        if (this.props.currentPage < this.props.totalPages - 1) {
            this.props.onPageChange(this.props.currentPage + 1);
        }
    }

    onPageClicked = (page) => {
        this.props.onPageChange(page);
    }

    renderPageNumbers = (visiblePagesCount) => {
        const pageNums = Array.from(
            {length: visiblePagesCount},
            (_, i) => {
                if (this.props.currentPage + 1 <= 3) {
                    return i + 1;
                } else if (this.props.currentPage + 1 >= this.props.totalPages - 2) {
                    return i + 1 + this.props.totalPages - visiblePagesCount;
                }
                return i + this.props.currentPage - 1;
            }
        );

        return pageNums.map((page, index) => {
            const className = `item${this.props.currentPage + 1 === page ? ' active' : ''}` ;
            return (
                <a key={index} onClick={() => this.onPageClicked(page - 1)}
                   className={className}
                >
                    {page}
                </a>
            );
        });
    }

    render() {
        return (
            <tr>
                <th colSpan={this.props.headNames.length}>
                    <div className="ui right floated pagination menu">
                        <a onClick={() => this.onPageClicked(0)} className="icon item">
                            <i className="angle double left icon"></i>
                        </a>
                        <a onClick={() => this.onPrevious()} className="icon item">
                            <i className="left chevron icon"></i>
                        </a>
                        {this.renderPageNumbers(this.props.totalPages < 5 ? this.props.totalPages : 5)}
                        <a onClick={() => this.onNext()} className="icon item">
                            <i className="right chevron icon"></i>
                        </a>
                        <a onClick={() => this.onPageClicked(this.props.totalPages - 1)} className="icon item">
                            <i className="angle double right icon"></i>
                        </a>
                    </div>
                </th>
            </tr>
        );
    }
}

export default Pages;