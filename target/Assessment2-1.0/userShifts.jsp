<%-- 
    Document   : index
    Created on : Jan 19, 2024, 3:15:48 AM
    Author     : andre
--%>

<%@page import="java.util.stream.Collectors"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.practicalexercises.assessment2.logic.Shift"%>
<%@page import="java.util.List"%>
<%@page import="com.practicalexercises.assessment2.logic.ProcedureEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/0_header.jsp"%>
<%@ include file="components/1_body_sideBar.jsp"%>
<%@ include file="components/2_pageContent.jsp"%>


<%!
    // Function to convert LocalDateTime to minutes and seconds
    String convertDateTimeToMinutesAndSeconds(LocalDateTime fromDate){
// Get the current local date and time
LocalDateTime toDate = LocalDateTime.now();

        // Calculate the duration in seconds
        long seconds = fromDate.until(toDate, ChronoUnit.SECONDS);

        // Calculate minutes and seconds
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%d:%02d minutes", minutes, remainingSeconds);
    }
%>

             
<!-- Begin Page Content -->
<div class="container-fluid">

    <%
            List<Shift> userShiftsList = (List) request.getSession().getAttribute("userShiftsList");
        %>
    
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">My Shifts</h1>
    <p class="mb-4">Here you can view the complete list of your shifts</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary center">Filter</h6>
            <form id="formFilter" class="user" action="SvShift" method="GET">
                <div class="form-group">
                    <input type="date" class="form-control form-control-user" id="dateFilter" name="dateFilter" placeholder="Date" required>
                    <div class="radio-buttons checkbox-form">
                        <div>
                            <input type="radio" id="attended" name="filterstate" value=true class="filter-radio" required>
                            <label for="attended" class="m-0 font-weight-bold text-primary center">Attended</label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block" style="width: 30%;">Apply Filter</button>
                        <div>
                            <input type="radio" id="waiting" name="filterstate" value=false class="filter-radio" required>
                            <label for="waiting" class="m-0 font-weight-bold text-primary center">Waiting</label>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Username Citizen</th>
                            <th>Procedure</th>
                            <th>Additional Info</th>
                            <th>Start Time Shift</th>
                            <th>Time since Shift</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Username Citizen</th>
                            <th>Procedure</th>
                            <th>Additional Info</th>
                            <th>Start Time Shift</th>
                            <th>Time since Shift</th>
                            <th>Status</th>
                        </tr>
                    </tfoot>

                    
                    <tbody>
                        <% for( Shift shift : userShiftsList){ %>
                        <tr>
                            <!--ID-->
                            <td><%= shift.getId() %> </td>
                            
                            <!--USERNAME-->
                            <td><%= shift.getCitizen().getUsername() %></td>
                            
                            <!--PROCEDURE-->
                            <td><%= shift.getProcedure().getTitle()%></td>
                            
                            <!--ADDITIONAL INFO-->
                            <td><%= truncateText(shift.getAdditionalInformation())%></td>
                            
                            <!--START TIME-->
                            <%
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                String formattedDateTime = shift.getDateHour().format(formatter);
                            %>

                            <td><%= formattedDateTime%></td>
                            <!--TIME SINCE START SHIFT-->
                            <% if (!shift.isShiftStatus()) {%>
                            <td>
                                <%= convertDateTimeToMinutesAndSeconds(shift.getDateHour())%>
                            </td>
                                <%} else {%>
                            <td>
                                --
                            </td>

                            <% } %>
                            <td>
                                <% if (shift.isShiftStatus()) { %>
                                <button class="btn btn-user btn-block attended-btn" name="<%= shift.getId() %>">ATTENDED</button>
                                <% } else { %>
                                <button class="btn btn-user btn-block delete-btn waiting-btn">WAITING</button>
                                <% }%>
                                <form name="edit" action="SvStateShift" class="hidden" method="POST"> 
                        <% if (shift.isShiftStatus() == true) {
                        %>
                        <button type="submit" class="hidden" ></button>
                        <% } else { 
                        %>
                        <button type="submit" class="state-btn hidden">button</button>
                        <% }%>

                        <input type="hidden" name="id" value="<%= shift.getId()%>">
                    </form>
                            </td>

                        </tr>
                                
                                <% }%>
                    </tbody>

                    <%!
                        // Truncate or cut text area until 25 index, and after ... (3 points)
                        public String truncateText(String text) {
                            if (text != null && text.length() > 25) {
                                return text.substring(0, 25) + "...";
                            }
                            return text;
                        }
                    %>

                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid 

<%@ include file="components/3_footer_logout.jsp"%>

<!--Script to display error messages (viewShifts.jsp)-->
<script src="js/state.js"></script>
<!--Script to display error messages (signin.jsp)-->
    <script src="js/registerValidation.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>

</body>
</html>