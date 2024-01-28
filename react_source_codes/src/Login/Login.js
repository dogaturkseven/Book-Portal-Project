import { Form, Input} from "antd";
import {Button} from "reactstrap";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import {Col, Container,  Row} from "react-bootstrap"
import AuthService from './service/Authservice'
import uiImg from './images/people.jpg'
import './Login.css'

const Login = () => {
  const navigate = useNavigate();
  const [credentials, setCredentials] = useState({});

  const onFinish = async (values) => {
    console.log("Success:", values);

    const response = await AuthService.signin(credentials);
    if (response) {
      navigate("/home");
    }

  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  const handleChange = (event) => {
    setCredentials({
      ...credentials,
      [event.target.name]: event.target.value
    });
  };

  return (
    <>
        <Container className = "mt-5">
            <Row>
                <Col lg={4} md={6} sm={12} className ="text-center ">
                <h1 className="icon-text">BOOK PORTAL</h1>
                    <Form
                      name="basic"
                      labelCol={{
                        span: 8
                      }}
                      wrapperCol={{
                        span: 16
                      }}
                      initialValues={{
                        remember: true
                      }}
                      onFinish={onFinish}
                      onFinishFailed={onFinishFailed}
                      style={{ margin: "0 auto", width: 400 }}
                    >
                      <Form.Item
                        label="Username"
                        name="username"
                        rules={[
                          {
                            required: true,
                            message: "Please input your username!"
                          }
                        ]}
                      >
                        <Input
                          onChange={handleChange}
                          name="username"
                          value={credentials.username}
                        />
                      </Form.Item>

                      <Form.Item
                        label="Password"
                        name="password"
                        rules={[
                          {
                            required: true,
                            message: "Please input your password!"
                          }
                        ]}
                      >
                        <Input.Password
                          onChange={handleChange}
                          name="password"
                          value={credentials.password}
                        />
                      </Form.Item>

                      <Form.Item
                        wrapperCol={{
                          offset: 8,
                          span: 16
                        }}
                      >
                        <Button  className="submit-button" color="primary" htmlType="submit">
                          Submit
                        </Button>
                      </Form.Item>
                    </Form>
                  </Col>

            <Col lg={8} md={6} sm={12}>
                <img className="people-img" src={uiImg} alt=""/>
            </Col>
        </Row>
    </Container>
    </>
  );
};

export default Login;