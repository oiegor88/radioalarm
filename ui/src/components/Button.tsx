import React from "react";

interface ButtonProps {
    label: string
    color: string
}

const Button: React.FC<ButtonProps> = ({ label, color }) => (
    <div role="button"
         className={ color + " border border-gray-600 px-2 py-1 m-0.5 text-center text-white rounded" }>
        { label }
    </div>
);

export default Button
