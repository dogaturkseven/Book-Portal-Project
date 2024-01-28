import styled from 'styled-components';
import { Container, Table, Form, FormGroup, Button, ButtonGroup } from 'react-bootstrap';

const StyledContainer = styled(Container)`
  margin-top: 50px;
`;

const StyledTable = styled(Table)`
  margin-top: 20px;

  th, td {
    text-align: center;
  }

  th {
    background-color: #f2f2f2;
  }
`;

const StyledButtonGroup = styled(ButtonGroup)`
  & > * {
    width: 80px; // Adjust the width as needed
  }
`;

const StyledForm = styled(Form)`
  max-width: 400px;
  margin: 0 auto;
`;

const StyledButtonGroupForm = styled(FormGroup)`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
`;

const CenteredHeading = styled.h3`
  text-align: center;
  margin-bottom: 20px;
`;


const StyledButton = styled(Button)`
  margin-right: 5px;
`;

export { StyledContainer, StyledTable, CenteredHeading, StyledForm, StyledButtonGroupForm, StyledButton, StyledButtonGroup};
