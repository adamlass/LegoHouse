<table>
                <tr>
                    <th></th>
                    <th>4x2 blocks</th>
                    <th>2x2 blocks</th>
                    <th>1x2 blocks</th>
                    <th>Door</th>
                    <th>Window</th>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><%= conf.getFourTwo()%></td>
                    <td><%= conf.getTwoTwo()%></td>
                    <td><%= conf.getOneTwo()%></td>
                    <td><%= conf.isDoor()%></td>
                    <td><%= "" + conf.isWindow()%></td>
                </tr>
            </table>