<%-- 
    Document   : Home
    Created on : Aug 27, 2021, 9:59:09 PM
    Author     : Dao Van Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <h1>World Population</h1>
    <style>
        h1 {
            color:#c00;
            font-family:sans-serif;
            font-size:2em;
            margin-bottom:0;
        }

        table {  
            font-family:sans-serif;
            th, td {
                padding:.25em .5em;
                text-align:left;
                &:nth-child(2) {
                    text-align:right;
                }
            }
            td {
                background-color:#eee;    
            }
            th {
                background-color:#009;
                color:#fff;
            }
        }

        .zigzag {
            border-collapse:separate;
            border-spacing:.25em 1em;
            tbody tr:nth-child(odd) {
                transform:rotate(2deg);
            }
            thead tr,
            tbody tr:nth-child(even) {
                transform:rotate(-2deg);
            }
        }

        input[type=text] {
            width: 130px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-image: url('searchicon.png');
            background-position: 10px 10px; 
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }

        input[type=text]:focus {
            width: 100%;
        }
        table, th, td {
            border: 1px solid black;
        }
    </style>

    <table class="zigzag">
        <thead>
            <tr>
                <th class="header">World Population</th>
                <th class="header">Total Countries</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${worldPopulation}</td>
                <td>${totalCountries}</td>
            </tr>
        </tbody>
    </table>
    <a href="GetAllCountries" class="button"><button>List Countries </button></a>
</html>
